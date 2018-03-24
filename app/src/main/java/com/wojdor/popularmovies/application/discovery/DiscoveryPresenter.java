package com.wojdor.popularmovies.application.discovery;

import com.wojdor.popularmovies.data.model.MovieModel;
import com.wojdor.popularmovies.data.response.MoviesResponse;
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

    DiscoveryPresenter(DiscoveryContract.View view) {
        this.view = view;
    }

    @Override
    public void onAttachView() {
        loadPopularMovies();
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
                .subscribe(this::onLoadResponse, this::onLoadError));
    }

    @Override
    public void loadTopRatedMovies() {
        disposables.add(MoviesService.getInstance().getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadResponse, this::onLoadError));
    }

    private void onLoadResponse(MoviesResponse moviesResponse) {
        List<MovieModel> movieModels = moviesResponse.getResults();
        List<Movie> movies = MovieModelMapper.getInstance().map(movieModels);
        onLoadSuccess(movies);
    }

    @Override
    public void onLoadSuccess(List<Movie> movies) {
        view.hideError();
        view.showMovies(movies);
        view.scrollToTop();
    }

    @Override
    public <T extends Throwable> void onLoadError(T error) {
        view.showError();
    }

    @Override
    public void openMovieDetails(Movie movie) {
        view.showMovieDetails(movie);
    }
}
