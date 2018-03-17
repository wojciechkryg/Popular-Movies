package com.wojdor.popularmovies.data.response;

import com.google.gson.annotations.Expose;
import com.wojdor.popularmovies.data.model.MovieModel;

import java.util.List;

public class MoviesResponse {

    @Expose
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }
}
