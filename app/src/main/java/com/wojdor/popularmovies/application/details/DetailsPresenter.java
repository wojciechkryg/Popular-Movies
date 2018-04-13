package com.wojdor.popularmovies.application.details;

import android.util.Log;

import com.wojdor.popularmovies.data.model.ReviewModel;
import com.wojdor.popularmovies.data.model.TrailerModel;
import com.wojdor.popularmovies.data.response.ReviewsResponse;
import com.wojdor.popularmovies.data.response.TrailersResponse;
import com.wojdor.popularmovies.data.source.device.MoviesDatabase;
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

    private final static int FIRST_POSITION = 0;

    private final DetailsContract.View view;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MoviesDatabase database;
    private final Movie movie;

    private List<Trailer> trailers;

    public DetailsPresenter(DetailsContract.View view, MoviesDatabase database, Movie movie) {
        this.view = view;
        this.database = database;
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
        view.showReviews(reviews);
    }

    @Override
    public void loadTrailers() {
        disposables.add(MoviesService.getInstance().getTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadTrailersResponse, this::onLoadError));
    }

    @Override
    public void openTrailer(Trailer trailer) {
        view.showTrailer(trailer.getVideoUrl());
    }

    @Override
    public void addMovieToFavourites() {
        database.add(movie);
        view.setupFavouriteIcon();
    }

    @Override
    public void deleteMovieFromFavourites() {
        database.delete(movie);
        view.setupFavouriteIcon();
    }

    @Override
    public boolean isMovieFavourite() {
        return database.contains(movie);
    }

    @Override
    public void shareMovie() {
        if (trailers.isEmpty()) return;
        view.shareTrailer(trailers.get(FIRST_POSITION).getVideoUrl());
    }

    private void onLoadTrailersResponse(TrailersResponse trailersResponse) {
        List<TrailerModel> trailerModels = trailersResponse.getResults();
        trailers = TrailerModelMapper.getInstance().map(trailerModels);
        view.showTrailers(trailers);
        enableShare();
    }

    private void enableShare() {
        if (trailers.isEmpty()) return;
        view.showShareMenuItem();
    }

    private <T extends Throwable> void onLoadError(T error) {
        Log.e(DetailsPresenter.class.getSimpleName(), error.getMessage());
    }
}
