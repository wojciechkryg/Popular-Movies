package com.wojdor.popularmovies.data.source.device;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.wojdor.popularmovies.data.source.device.MoviesContract.BASE_CONTENT_URI;
import static com.wojdor.popularmovies.data.source.device.MoviesContract.CONTENT_AUTHORITY;
import static com.wojdor.popularmovies.data.source.device.MoviesContract.MovieEntry;

public class MoviesProvider extends ContentProvider {

    public final static Uri CONTENT_URI = BASE_CONTENT_URI;

    private MoviesDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new MoviesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return dbHelper.getReadableDatabase().query(
                MovieEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = insert(values);
        return refreshAfterInsert(id);
    }

    private long insert(ContentValues values) {
        return dbHelper.getWritableDatabase().insert(
                MovieEntry.TABLE_NAME,
                null,
                values);
    }

    private Uri refreshAfterInsert(long rowId) {
        Uri uri = ContentUris.withAppendedId(BASE_CONTENT_URI, rowId);
        refresh(uri);
        return uri;
    }

    private void refresh(Uri uri) {
        if (getContext() == null) return;
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = delete(selection, selectionArgs);
        refresh(uri);
        return count;
    }

    private int delete(@Nullable String selection, @Nullable String[] selectionArgs) {
        return dbHelper.getWritableDatabase().delete(
                MovieEntry.TABLE_NAME,
                selection,
                selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = update(values, selection, selectionArgs);
        refresh(uri);
        return count;
    }

    private int update(@Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return dbHelper.getWritableDatabase().update(
                MovieEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
