package com.wojdor.popularmovies.data.response;

import com.google.gson.annotations.Expose;
import com.wojdor.popularmovies.data.model.TrailerModel;

import java.util.List;

public class TrailersResponse {

    @Expose
    private List<TrailerModel> results;

    public List<TrailerModel> getResults() {
        return results;
    }
}
