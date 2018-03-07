package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.application.base.BasePresenter;
import com.wojdor.popularmovies.application.base.BaseView;
import com.wojdor.popularmovies.domain.Movie;

interface DetailsContract {

    interface View extends BaseView {

        void showDetails(Movie movie);
    }

    interface Presenter extends BasePresenter {
    }
}
