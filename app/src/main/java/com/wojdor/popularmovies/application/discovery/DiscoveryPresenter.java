package com.wojdor.popularmovies.application.discovery;

public class DiscoveryPresenter implements DiscoveryContract.Presenter {

    private final DiscoveryContract.View view;

    public DiscoveryPresenter(DiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    public void loadPopularMovies() {
        new DownloadMoviesTask(view).execute();
    }

    @Override
    public void loadTopRatedMovies() {

    }
}
