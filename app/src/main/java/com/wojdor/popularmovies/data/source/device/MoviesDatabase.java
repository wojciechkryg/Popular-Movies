package com.wojdor.popularmovies.data.source.device;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.wojdor.popularmovies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.wojdor.popularmovies.data.source.device.MoviesContract.MovieEntry;
import static com.wojdor.popularmovies.data.source.device.MoviesProvider.CONTENT_URI;

public class MoviesDatabase {

    private static final int EMPTY_COUNT = 0;

    private final ContentResolver contentResolver;

    public MoviesDatabase(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public void add(Movie movie) {
        contentResolver.insert(CONTENT_URI, getContentValuesForMovie(movie));
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
        contentResolver.delete(CONTENT_URI,
                MovieEntry.COLUMN_ID + "=" + movie.getId(), null);
    }

    public boolean contains(Movie movie) {
        Cursor cursor = contentResolver.query(CONTENT_URI, null,
                MovieEntry.COLUMN_ID + "=" + movie.getId(), null, null);
        boolean contains = cursor != null && cursor.getCount() > EMPTY_COUNT;
        close(cursor);
        return contains;
    }

    public List<Movie> getAll() {
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        List<Movie> movies = new ArrayList<>();
        if (cursor == null) return movies;
        while (cursor.moveToNext()) {
            movies.add(getMovieFromCursor(cursor));
        }
        close(cursor);
        return movies;
    }

    private Movie getMovieFromCursor(Cursor cursor) {
        return new Movie(
                cursor.getInt(cursor.getColumnIndex(MovieEntry.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE)),
                cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_POSTER_URL)),
                cursor.getDouble(cursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)),
                cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_OVERVIEW))
        );
    }

    private void close(Cursor cursor) {
        if (cursor == null) return;
        cursor.close();
    }
}
