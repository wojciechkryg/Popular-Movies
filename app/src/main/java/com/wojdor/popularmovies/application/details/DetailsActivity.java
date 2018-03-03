package com.wojdor.popularmovies.application.details;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.domain.Movie;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String MOVIE_EXTRA = "movie_extra";

    private DetailsContract.Presenter presenter;
    private ImageView posterIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(R.string.detail);
        initViews();
        setupPresenter();
    }

    private void initViews() {
        posterIv = findViewById(R.id.activity_detail_poster_iv);
    }

    @Override
    public void setupPresenter() {
        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        presenter = new DetailsPresenter(this, movie);
        presenter.start();
    }

    @Override
    public void showDetails(Movie movie) {
        Picasso.with(this).load(movie.getPosterUrl()).into(posterIv);
    }
}
