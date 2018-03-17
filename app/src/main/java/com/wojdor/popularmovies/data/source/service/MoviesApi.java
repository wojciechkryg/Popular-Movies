package com.wojdor.popularmovies.data.source.service;

import com.wojdor.popularmovies.data.response.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMovies();

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies();
}
