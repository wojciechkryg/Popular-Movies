package com.wojdor.popularmovies.data.response;

import com.google.gson.annotations.Expose;
import com.wojdor.popularmovies.data.model.ReviewModel;

import java.util.List;

public class ReviewsResponse {

    @Expose
    private List<ReviewModel> results;

    public List<ReviewModel> getResults() {
        return results;
    }
}
