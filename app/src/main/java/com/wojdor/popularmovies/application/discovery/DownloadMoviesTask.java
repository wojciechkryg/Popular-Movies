package com.wojdor.popularmovies.application.discovery;

import android.os.AsyncTask;

import com.wojdor.popularmovies.data.source.MoviesRemoteDataSource;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public class DownloadMoviesTask extends AsyncTask<Void, Void, List<Movie>> {

    private DiscoveryContract.View view;

    public DownloadMoviesTask(DiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        return new MoviesRemoteDataSource().getPopularMovies();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        view.showMovies(movies);
    }
}
