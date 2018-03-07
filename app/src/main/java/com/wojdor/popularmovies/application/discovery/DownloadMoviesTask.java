package com.wojdor.popularmovies.application.discovery;

import android.os.AsyncTask;

import com.wojdor.popularmovies.data.source.MoviesRemoteDataSource;
import com.wojdor.popularmovies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

class DownloadMoviesTask extends AsyncTask<Void, Void, List<Movie>> {

    private final DiscoveryContract.Presenter presenter;
    private final MoviesOrder order;

    public DownloadMoviesTask(DiscoveryContract.Presenter presenter, MoviesOrder order) {
        this.presenter = presenter;
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
            presenter.onLoadError();
        } else {
            presenter.onLoadSuccess(movies);
        }
    }
}
