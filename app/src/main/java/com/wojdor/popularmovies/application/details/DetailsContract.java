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
    }

    interface Presenter extends BasePresenter {

        void loadReviews();

        void onReviewsLoadSuccess(List<Review> reviews);

        void loadTrailers();

        void onTrailersLoadSuccess(List<Trailer> trailers);

        <T extends Throwable> void onLoadError(T error);
    }
}
