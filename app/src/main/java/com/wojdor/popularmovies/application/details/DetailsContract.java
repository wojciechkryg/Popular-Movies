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
    }

    interface Presenter extends BasePresenter {

        void loadReviews();

        void loadTrailers();
    }
}
