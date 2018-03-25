package com.wojdor.popularmovies.data.utils;

import com.wojdor.popularmovies.data.model.MovieModel;
import com.wojdor.popularmovies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MovieModelMapper {

    private static MovieModelMapper instance;

    private MovieModelMapper() {
    }

    public static MovieModelMapper getInstance() {
        if (instance == null) {
            instance = new MovieModelMapper();
        }
        return instance;
    }

    private Movie map(MovieModel model) {
        return new Movie(
                model.getId(),
                model.getTitle(),
                model.getReleaseDate(),
                UrlUtils.getInstance().getPosterUrl(model.getPosterPath()),
                model.getVoteAverage(),
                model.getOverview()
        );
    }

    public List<Movie> map(List<MovieModel> movieModels) {
        List<Movie> movies = new ArrayList<>();
        for (MovieModel movieModel : movieModels) {
            movies.add(map(movieModel));
        }
        return movies;
    }
}
