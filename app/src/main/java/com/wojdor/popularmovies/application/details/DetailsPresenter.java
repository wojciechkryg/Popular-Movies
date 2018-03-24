package com.wojdor.popularmovies.application.details;

import com.wojdor.popularmovies.data.model.ReviewModel;
import com.wojdor.popularmovies.data.model.TrailerModel;
import com.wojdor.popularmovies.data.response.ReviewsResponse;
import com.wojdor.popularmovies.data.response.TrailersResponse;
import com.wojdor.popularmovies.data.source.service.MoviesService;
import com.wojdor.popularmovies.data.utils.ReviewModelMapper;
import com.wojdor.popularmovies.data.utils.TrailerModelMapper;
import com.wojdor.popularmovies.domain.Movie;
import com.wojdor.popularmovies.domain.Review;
import com.wojdor.popularmovies.domain.Trailer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

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
        loadReviews();
        loadTrailers();
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }

    @Override
    public void loadReviews() {
        disposables.add(MoviesService.getInstance().getReviews(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadReviewsResponse, this::onLoadError));
    }

    private void onLoadReviewsResponse(ReviewsResponse reviewsResponse) {
        List<ReviewModel> reviewModels = reviewsResponse.getResults();
        List<Review> reviews = ReviewModelMapper.getInstance().map(reviewModels);
        onReviewsLoadSuccess(reviews);
    }

    @Override
    public void onReviewsLoadSuccess(List<Review> reviews) {
        // TODO: show reviews
    }

    @Override
    public void loadTrailers() {
        disposables.add(MoviesService.getInstance().getTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadTrailersResponse, this::onLoadError));
    }

    private void onLoadTrailersResponse(TrailersResponse trailersResponse) {
        List<TrailerModel> trailerModels = trailersResponse.getResults();
        List<Trailer> trailers = TrailerModelMapper.getInstance().map(trailerModels);
        onTrailersLoadSuccess(trailers);
    }

    @Override
    public void onTrailersLoadSuccess(List<Trailer> trailers) {
        // TODO: show trailers
    }

    @Override
    public <T extends Throwable> void onLoadError(T error) {
        // TODO: show error
    }
}
