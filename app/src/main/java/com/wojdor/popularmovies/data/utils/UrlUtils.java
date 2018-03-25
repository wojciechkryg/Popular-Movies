package com.wojdor.popularmovies.data.utils;

public final class UrlUtils {

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";
    private static final String BASE_TRAILER_VIDEO_URL = "https://www.youtube.com/watch?v=";
    private static final String BASE_TRAILER_THUMBNAIL_URL = "http://img.youtube.com/vi/";
    private static final String THUMBNAIL_NUMBER = "/0.jpg";

    private static UrlUtils instance;

    private UrlUtils() {
    }

    public static UrlUtils getInstance() {
        if (instance == null) {
            instance = new UrlUtils();
        }
        return instance;
    }

    public String getPosterUrl(String endpoint) {
        return BASE_POSTER_URL + endpoint;
    }

    public String getTrailerVideoUrl(String key) {
        return BASE_TRAILER_VIDEO_URL + key;
    }

    public String getTrailerThumbnailUrl(String key) {
        return BASE_TRAILER_THUMBNAIL_URL + key + THUMBNAIL_NUMBER;
    }
}
