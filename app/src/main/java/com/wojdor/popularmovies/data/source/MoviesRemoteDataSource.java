package com.wojdor.popularmovies.data.source;

import android.util.Log;

import com.wojdor.popularmovies.data.mapper.MovieModelMapper;
import com.wojdor.popularmovies.data.model.MovieModel;
import com.wojdor.popularmovies.data.utils.MovieModelUtils;
import com.wojdor.popularmovies.data.utils.NetworkUtils;
import com.wojdor.popularmovies.domain.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesRemoteDataSource implements MoviesDataSource {

    private static final String BASE_URL = "http://api.themoviedb.org/3";
    private static final String POPULAR_MOVIES_ENDPOINT = "movie/popular";
    private static final String TOP_RATED_MOVIES_ENDPOINT = "movie/top_rated";

    @Override
    public List<Movie> getPopularMovies() {
        URL url = NetworkUtils.getInstance().buildUrl(BASE_URL, POPULAR_MOVIES_ENDPOINT);
        try {
            String jsonResponse = NetworkUtils.getInstance().getJsonResponseFromHttpUrl(url);
            List<MovieModel> movieModels = MovieModelUtils.getInstance().
                    getMovieModelsFromJson(jsonResponse);
            return MovieModelMapper.getInstance().map(movieModels);
        } catch (IOException | JSONException error) {
            Log.e(this.getClass().getName(), error.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Movie> getTopRatedMovies() {
        return null;
    }
}
