package com.wojdor.popularmovies.data.source;

import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public interface MoviesDataSource {

    List<Movie> getPopularMovies();

    List<Movie> getTopRatedMovies();
}
