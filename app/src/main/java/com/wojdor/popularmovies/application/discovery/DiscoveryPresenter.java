package com.wojdor.popularmovies.application.discovery;

public class DiscoveryPresenter implements DiscoveryContract.Presenter {

    private final DiscoveryContract.View view;

    public DiscoveryPresenter(DiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadPopularMovies();
    }

    @Override
    public void loadPopularMovies() {
        new DownloadMoviesTask(view, MoviesOrder.POPULAR).execute();
    }

    @Override
    public void loadTopRatedMovies() {
        new DownloadMoviesTask(view, MoviesOrder.TOP_RATED).execute();
    }
}
