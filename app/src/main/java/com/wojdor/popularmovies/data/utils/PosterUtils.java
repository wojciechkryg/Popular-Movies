package com.wojdor.popularmovies.data.utils;

public final class PosterUtils {

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";

    private static PosterUtils instance;

    private PosterUtils() {
    }

    public static PosterUtils getInstance() {
        if (instance == null) {
            instance = new PosterUtils();
        }
        return instance;
    }

    public String getPosterUrl(String endpoint) {
        return BASE_POSTER_URL + endpoint;
    }
}
