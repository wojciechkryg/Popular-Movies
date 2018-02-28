package com.wojdor.popularmovies.domain;


public class Movie {

    private String title;
    private String releaseDate;
    private String posterUrl;
    private double voteAverage;
    private String overview;

    public Movie(String title, String releaseDate, String posterUrl, double voteAverage,
                 String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}