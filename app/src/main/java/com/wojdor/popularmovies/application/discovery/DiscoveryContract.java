package com.wojdor.popularmovies.application.discovery;

import com.wojdor.popularmovies.application.base.BasePresenter;
import com.wojdor.popularmovies.application.base.BaseView;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

interface DiscoveryContract {

    interface View extends BaseView {

        MenuItem getCurrentMenuItem();

        void showMovies(List<Movie> movies);

        void showError();

        void hideError();

        void restoreMoviesState();

        void showMovieDetails(Movie movie);
    }

    interface Presenter extends BasePresenter {

        void loadPopularMovies();

        void loadTopRatedMovies();

        void loadFavouriteMovies();

        void openMovieDetails(Movie movie);
    }
}
