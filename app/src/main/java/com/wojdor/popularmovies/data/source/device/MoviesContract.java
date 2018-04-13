package com.wojdor.popularmovies.data.source.device;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.wojdor.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns {

        public static String TABLE_NAME = "movies";
        public static String COLUMN_ID = "_id";
        public static String COLUMN_TITLE = "title";
        public static String COLUMN_RELEASE_DATE = "releaseDate";
        public static String COLUMN_POSTER_URL = "posterUrl";
        public static String COLUMN_VOTE_AVERAGE = "voteAverage";
        public static String COLUMN_OVERVIEW = "overview";
    }
}
