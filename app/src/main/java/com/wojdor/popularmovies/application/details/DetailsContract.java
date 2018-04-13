package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.application.base.BasePresenter;
import com.wojdor.popularmovies.application.base.BaseView;
import com.wojdor.popularmovies.domain.Movie;
import com.wojdor.popularmovies.domain.Review;
import com.wojdor.popularmovies.domain.Trailer;

import java.util.List;

interface DetailsContract {

    interface View extends BaseView {

        void showDetails(Movie movie);

        void showReviews(List<Review> reviews);

        void showTrailers(List<Trailer> trailers);

        void showTrailer(String videoUrl);

        void setupFavouriteIcon();
    }

    interface Presenter extends BasePresenter {

        void loadReviews();

        void loadTrailers();

        void openTrailer(Trailer trailer);

        void addMovieToFavourites();

        void deleteMovieFromFavourites();

        boolean isMovieFavourite();
    }
}
