package com.wojdor.popularmovies.data.model;

public class MovieModel {

    private final String title;
    private final String releaseDate;
    private final String posterPath;
    private final double voteAverage;
    private final String overview;

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
