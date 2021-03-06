package com.wojdor.popularmovies.application.discovery;

import com.wojdor.popularmovies.data.model.MovieModel;
import com.wojdor.popularmovies.data.response.MoviesResponse;
import com.wojdor.popularmovies.data.source.device.MoviesDatabase;
import com.wojdor.popularmovies.data.source.service.MoviesService;
import com.wojdor.popularmovies.data.utils.MovieModelMapper;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DiscoveryPresenter implements DiscoveryContract.Presenter {

    private final DiscoveryContract.View view;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MoviesDatabase database;

    DiscoveryPresenter(DiscoveryContract.View view, MoviesDatabase database) {
        this.view = view;
        this.database = database;
    }

    @Override
    public void onAttachView() {
        switch (view.getCurrentMenuItem()) {
            case POPULAR:
                loadPopularMovies();
                break;
            case TOP_RATED:
                loadTopRatedMovies();
                break;
            case FAVOURITE:
                loadFavouriteMovies();
                break;
            default:
                loadPopularMovies();
        }
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }

    @Override
    public void loadPopularMovies() {
        disposables.add(MoviesService.getInstance().getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadMoviesResponse, this::onLoadError));
    }

    @Override
    public void loadTopRatedMovies() {
        disposables.add(MoviesService.getInstance().getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadMoviesResponse, this::onLoadError));
    }

    private void onLoadMoviesResponse(MoviesResponse moviesResponse) {
        List<MovieModel> movieModels = moviesResponse.getResults();
        List<Movie> movies = MovieModelMapper.getInstance().map(movieModels);
        onLoadMovies(movies);
    }

    private <T extends Throwable> void onLoadError(T error) {
        view.showError();
    }

    @Override
    public void loadFavouriteMovies() {
        onLoadMovies(database.getAll());
    }

    private void onLoadMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            view.showError();
            return;
        }
        view.hideError();
        view.showMovies(movies);
        view.restoreMoviesState();
    }

    @Override
    public void openMovieDetails(Movie movie) {
        view.showMovieDetails(movie);
    }
}
