package com.wojdor.popularmovies.data.utils;

import android.support.annotation.NonNull;

import com.wojdor.popularmovies.data.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class MovieModelUtils {

    private static final String RESULTS_KEY = "results";
    private static final String TITLE_KEY = "title";
    private static final String RELEASE_DATE_KEY = "release_date";
    private static final String POSTER_PATH_KEY = "poster_path";
    private static final String VOTE_AVERAGE_KEY = "vote_average";
    private static final String OVERVIEW_KEY = "overview";

    private static MovieModelUtils instance;

    private MovieModelUtils() {
    }

    public static MovieModelUtils getInstance() {
        if (instance == null) {
            instance = new MovieModelUtils();
        }
        return instance;
    }

    public List<MovieModel> getMovieModelsFromJson(String json) throws JSONException {
        JSONObject response = new JSONObject(json);
        List<JSONObject> movieObjects = getMovieObjects(response);
        return getMovieModels(movieObjects);
    }

    private List<MovieModel> getMovieModels(List<JSONObject> movieObjects) {
        List<MovieModel> movieModels = new ArrayList<>();
        for (JSONObject movieObject : movieObjects) {
            MovieModel movieModel = getMovieModel(movieObject);
            movieModels.add(movieModel);
        }
        return movieModels;
    }

    private MovieModel getMovieModel(JSONObject movieObject) {
        String title = movieObject.optString(TITLE_KEY);
        String releaseDate = movieObject.optString(RELEASE_DATE_KEY);
        String posterPath = movieObject.optString(POSTER_PATH_KEY);
        double voteAverage = movieObject.optDouble(VOTE_AVERAGE_KEY);
        String overview = movieObject.optString(OVERVIEW_KEY);
        return new MovieModel(title, releaseDate, posterPath, voteAverage, overview);
    }

    @NonNull
    private List<JSONObject> getMovieObjects(JSONObject response) throws JSONException {
        List<JSONObject> movieObjects = new ArrayList<>();
        JSONArray movieArray = response.getJSONArray(RESULTS_KEY);
        for (int i = 0; i < movieArray.length(); i++) {
            movieObjects.add(movieArray.getJSONObject(i));
        }
        return movieObjects;
    }
}
