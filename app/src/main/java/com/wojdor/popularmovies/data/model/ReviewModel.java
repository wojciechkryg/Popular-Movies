package com.wojdor.popularmovies.data.model;

import com.google.gson.annotations.Expose;

public class ReviewModel {

    @Expose
    private String author;
    @Expose
    private String content;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
