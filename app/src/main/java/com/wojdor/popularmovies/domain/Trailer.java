package com.wojdor.popularmovies.domain;

public class Trailer {

    private String videoUrl;
    private String thumbnailUrl;
    private String name;

    public Trailer(String videoUrl, String thumbnailUrl, String name) {
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getName() {
        return name;
    }
}
