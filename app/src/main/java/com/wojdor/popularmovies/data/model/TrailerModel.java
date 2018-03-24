package com.wojdor.popularmovies.data.model;

import com.google.gson.annotations.Expose;

public class TrailerModel {

    @Expose
    private String key;
    @Expose
    private String name;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
