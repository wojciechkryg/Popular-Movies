package com.wojdor.popularmovies.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private final String title;
    private final String releaseDate;
    private final String posterUrl;
    private final double voteAverage;
    private final String overview;

    public Movie(String title, String releaseDate, String posterUrl, double voteAverage,
                 String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    private Movie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        posterUrl = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterUrl);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
    }
}
