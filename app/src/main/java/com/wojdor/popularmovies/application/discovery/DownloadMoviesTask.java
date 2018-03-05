package com.wojdor.popularmovies.application.discovery;

import android.os.AsyncTask;

import com.wojdor.popularmovies.data.source.MoviesRemoteDataSource;
import com.wojdor.popularmovies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class DownloadMoviesTask extends AsyncTask<Void, Void, List<Movie>> {

    private DiscoveryContract.View view;
    private MoviesOrder order;

    public DownloadMoviesTask(DiscoveryContract.View view, MoviesOrder order) {
        this.view = view;
        this.order = order;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        MoviesRemoteDataSource dataSource = new MoviesRemoteDataSource();
        switch (order) {
            case POPULAR:
                return dataSource.getPopularMovies();
            case TOP_RATED:
                return dataSource.getTopRatedMovies();
            default:
                return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies.isEmpty()) {
            view.showError();
        } else {
            view.hideError();
            view.showMovies(movies);
            view.scrollToTop();
        }
    }
}
