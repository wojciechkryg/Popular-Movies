package com.wojdor.popularmovies.application.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.application.details.DetailsActivity;
import com.wojdor.popularmovies.application.discovery.adapter.DiscoveryAdapter;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public class DiscoveryActivity extends BaseActivity implements DiscoveryContract.View {

    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int FIRST_POSITION = 0;

    private DiscoveryContract.Presenter presenter;
    private Menu menu;
    private TextView noConnectionTv;
    private RecyclerView moviesRv;
    private DiscoveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        setTitle(R.string.popular);
        initViews();
        setupMoviesRv();
        setupPresenter();
    }

    private void initViews() {
        noConnectionTv = findViewById(R.id.activity_discovery_no_connection_tv);
        moviesRv = findViewById(R.id.activity_discovery_movies_rv);
    }

    private void setupMoviesRv() {
        adapter = new DiscoveryAdapter(movie -> presenter.openMovieDetails(movie));
        moviesRv.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        moviesRv.setAdapter(adapter);
        moviesRv.addOnScrollListener(getOnScrollListener());
    }

    @NonNull
    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    presenter.loadMoreMovies();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.discovery_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                handleOnPopularMenuItemClick();
                return true;
            case R.id.action_top_rated:
                handleOnTopRatedMenuItemClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleOnPopularMenuItemClick() {
        setTitle(R.string.popular);
        presenter.loadPopularMovies();
        hidePopularMenuItem();
        showTopRatedMenuItem();
    }

    private void handleOnTopRatedMenuItemClick() {
        setTitle(R.string.top_rated);
        presenter.loadTopRatedMovies();
        hideTopRatedMenuItem();
        showPopularMenuItem();
    }

    private void hidePopularMenuItem() {
        menu.findItem(R.id.action_popular).setVisible(false);
    }

    private void showPopularMenuItem() {
        menu.findItem(R.id.action_popular).setVisible(true);
    }

    private void hideTopRatedMenuItem() {
        menu.findItem(R.id.action_top_rated).setVisible(false);
    }

    private void showTopRatedMenuItem() {
        menu.findItem(R.id.action_top_rated).setVisible(true);
    }

    @Override
    public void setupPresenter() {
        presenter = new DiscoveryPresenter(this);
        presenter.start();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void showError() {
        moviesRv.setVisibility(View.GONE);
        noConnectionTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        noConnectionTv.setVisibility(View.GONE);
        moviesRv.setVisibility(View.VISIBLE);
    }

    @Override
    public void scrollToTop() {
        moviesRv.scrollToPosition(FIRST_POSITION);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.MOVIE_EXTRA, movie);
        startActivity(intent);
    }
}
