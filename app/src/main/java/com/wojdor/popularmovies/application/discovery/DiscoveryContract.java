package com.wojdor.popularmovies.application.discovery;

import com.wojdor.popularmovies.application.base.BasePresenter;
import com.wojdor.popularmovies.application.base.BaseView;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public interface DiscoveryContract {

    interface View extends BaseView {

        void showMovies(List<Movie> movies);

        void scrollToTop();

        void showMovieDetails(Movie movie);
    }

    interface Presenter extends BasePresenter {

        void loadPopularMovies();

        void loadTopRatedMovies();

        void openMovieDetails(Movie movie);
    }
}
