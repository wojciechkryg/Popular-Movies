package com.wojdor.popularmovies.application.discovery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public class DiscoveryActivity extends BaseActivity implements DiscoveryContract.View {

    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int FIRST_POSITION = 0;

    private DiscoveryContract.Presenter presenter;
    private Menu menu;
    private RecyclerView moviesRv;
    private DiscoveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        setTitle(R.string.popular);
        setupMoviesRv();
        setupPresenter();
    }

    private void setupMoviesRv() {
        moviesRv = findViewById(R.id.activity_discovery_movies_rv);
        adapter = new DiscoveryAdapter();
        moviesRv.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        moviesRv.setAdapter(adapter);
    }

    private void setupPresenter() {
        presenter = new DiscoveryPresenter(this);
        presenter.start();
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

    public void hidePopularMenuItem() {
        menu.findItem(R.id.action_popular).setVisible(false);
    }

    public void showPopularMenuItem() {
        menu.findItem(R.id.action_popular).setVisible(true);
    }

    public void hideTopRatedMenuItem() {
        menu.findItem(R.id.action_top_rated).setVisible(false);
    }

    public void showTopRatedMenuItem() {
        menu.findItem(R.id.action_top_rated).setVisible(true);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void scrollToTop() {
        moviesRv.scrollToPosition(FIRST_POSITION);
    }
}
