package com.wojdor.popularmovies.data.model;

public class MovieModel {

    private String title;
    private String releaseDate;
    private String posterPath;
    private double voteAverage;
    private String overview;

    public MovieModel(String title, String releaseDate, String posterPath, double voteAverage,
                      String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}
