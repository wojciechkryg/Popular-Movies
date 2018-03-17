package com.wojdor.popularmovies.data.source.service;

public final class MoviesService {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static MoviesApi instance;

    private MoviesService() {
    }

    public static MoviesApi getInstance() {
        if (instance == null) {
            instance = ServiceGenerator.getInstance(BASE_URL).createService(MoviesApi.class);
        }
        return instance;
    }
}
