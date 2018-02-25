package com.wojdor.popularmovies.data.utils;

import android.net.Uri;
import android.util.Log;

import com.wojdor.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String API_KEY = "api_key";

    private static NetworkUtils instance;

    private NetworkUtils() {
    }

    public static NetworkUtils getInstance() {
        if (instance == null) {
            instance = new NetworkUtils();
        }
        return instance;
    }

    public URL buildUrl(String baseUrl, String endpoint) {
        Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendEncodedPath(endpoint)
                .appendQueryParameter(API_KEY, BuildConfig.TMDB_API_TOKEN)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException error) {
            Log.e(this.getClass().getName(), error.getMessage());
        }
        return url;
    }

    public String getJsonResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
