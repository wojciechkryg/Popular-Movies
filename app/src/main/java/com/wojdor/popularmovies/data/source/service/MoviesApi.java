package com.wojdor.popularmovies.data.source.service;

import com.wojdor.popularmovies.data.response.MoviesResponse;
import com.wojdor.popularmovies.data.response.ReviewsResponse;
import com.wojdor.popularmovies.data.response.TrailersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MoviesApi {

    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMovies();

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies();

    @GET("movie/{id}/reviews")
    Observable<ReviewsResponse> getReviews(@Path("id") int id);

    @GET("movie/{id}/videos")
    Observable<TrailersResponse> getTrailers(@Path("id") int id);
}
