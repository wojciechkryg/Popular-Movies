package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.domain.Movie;

public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View view;
    private final Movie movie;

    public DetailsPresenter(DetailsContract.View view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    @Override
    public void start() {
        view.showDetails(movie);
    }
}
