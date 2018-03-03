package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.domain.Movie;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private Movie movie;

    public DetailsPresenter(DetailsContract.View view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    @Override
    public void start() {
        view.showDetails(movie);
    }
}
