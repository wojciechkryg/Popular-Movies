package com.wojdor.popularmovies.data.source.device;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.wojdor.popularmovies.domain.Movie;

import static com.wojdor.popularmovies.data.source.device.MoviesContract.MovieEntry;

public class MoviesDatabase {

    private static final int EMPTY_COUNT = 0;

    private final ContentResolver contentResolver;

    public MoviesDatabase(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public void add(Movie movie) {
        contentResolver.insert(MoviesProvider.CONTENT_URI, getContentValuesForMovie(movie));
    }

    @NonNull
    private ContentValues getContentValuesForMovie(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MovieEntry.COLUMN_ID, movie.getId());
        values.put(MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(MovieEntry.COLUMN_POSTER_URL, movie.getPosterUrl());
        values.put(MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        return values;
    }

    public void delete(Movie movie) {
        contentResolver.delete(MoviesProvider.CONTENT_URI,
                MovieEntry.COLUMN_ID + "=" + movie.getId(), null);
    }

    public boolean contains(Movie movie) {
        Cursor cursor = contentResolver.query(MoviesProvider.CONTENT_URI, null,
                MovieEntry.COLUMN_ID + "=" + movie.getId(), null, null);
        return cursor != null && cursor.moveToFirst() && cursor.getCount() > EMPTY_COUNT;
    }
}
