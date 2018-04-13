package com.wojdor.popularmovies.application.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.application.details.DetailsActivity;
import com.wojdor.popularmovies.data.source.device.MoviesDatabase;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoveryActivity extends BaseActivity implements DiscoveryContract.View {

    private static final int MIN_NUMBER_OF_COLUMNS = 2;
    private static final int COLUMN_WIDTH_DIVIDER = 300;
    private static final int FIRST_POSITION = 0;

    @BindView(R.id.activity_discovery_navigation_bnv)
    BottomNavigationView navigationBnv;
    @BindView(R.id.activity_discovery_no_connection_tv)
    TextView noConnectionTv;
    @BindView(R.id.activity_discovery_movies_rv)
    RecyclerView moviesRv;

    private DiscoveryContract.Presenter presenter;
    private DiscoveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        ButterKnife.bind(this);
        setupPresenter();
        setTitle(R.string.popular);
        setupMoviesRv();
        setupNavigationBnv();
    }

    private void setupMoviesRv() {
        adapter = new DiscoveryAdapter(movie -> presenter.openMovieDetails(movie));
        moviesRv.setLayoutManager(new GridLayoutManager(this, calculateNumberOfColumns()));
        moviesRv.setAdapter(adapter);
    }

    private int calculateNumberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int numberOfColumns = displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER;
        if (numberOfColumns < MIN_NUMBER_OF_COLUMNS) return MIN_NUMBER_OF_COLUMNS;
        return numberOfColumns;
    }

    private void setupNavigationBnv() {
        navigationBnv.setOnNavigationItemSelectedListener(item -> {
            presenter.onDetachView();
            switch (item.getItemId()) {
                case R.id.action_popular:
                    handleOnPopularMenuItemClick();
                    break;
                case R.id.action_top_rated:
                    handleOnTopRatedMenuItemClick();
                    break;
                case R.id.action_favourites:
                    handleOnFavouritesMenuItemClick();
                    break;
                default:
                    return false;
            }
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    private void handleOnPopularMenuItemClick() {
        setTitle(R.string.popular);
        presenter.loadPopularMovies();
    }

    private void handleOnTopRatedMenuItemClick() {
        setTitle(R.string.top_rated);
        presenter.loadTopRatedMovies();
    }

    private void handleOnFavouritesMenuItemClick() {
        setTitle(R.string.favourites);
        presenter.loadFavouriteMovies();
    }

    @Override
    public void setupPresenter() {
        MoviesDatabase database = new MoviesDatabase(getContentResolver());
        presenter = new DiscoveryPresenter(this, database);
        presenter.onAttachView();
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
