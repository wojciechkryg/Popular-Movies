package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.domain.Movie;

import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View view;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final Movie movie;

    public DetailsPresenter(DetailsContract.View view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    @Override
    public void onAttachView() {
        view.showDetails(movie);
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }
}
