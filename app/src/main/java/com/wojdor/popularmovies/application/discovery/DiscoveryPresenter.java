package com.wojdor.popularmovies.application.discovery;

import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public class DiscoveryPresenter implements DiscoveryContract.Presenter {

    private final DiscoveryContract.View view;

    DiscoveryPresenter(DiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadPopularMovies();
    }

    @Override
    public void loadPopularMovies() {
        new DownloadMoviesTask(this, MoviesOrder.POPULAR).execute();
    }

    @Override
    public void loadTopRatedMovies() {
        new DownloadMoviesTask(this, MoviesOrder.TOP_RATED).execute();
    }

    @Override
    public void onLoadSuccess(List<Movie> movies) {
        view.hideError();
        view.showMovies(movies);
        view.scrollToTop();
    }

    @Override
    public void onLoadError() {
        view.showError();
    }

    @Override
    public void openMovieDetails(Movie movie) {
        view.showMovieDetails(movie);
    }
}
