package com.wojdor.popularmovies.application.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.domain.Movie;

import java.util.Locale;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String MOVIE_EXTRA = "MOVIE";
    private static final String AVERAGE_FORMAT = "%.1f";

    private DetailsContract.Presenter presenter;
    private TextView titleTv;
    private ImageView posterIv;
    private TextView releaseDateTv;
    private TextView voteAverageTv;
    private TextView overviewTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(R.string.details);
        initViews();
        setupPresenter();
    }

    private void initViews() {
        titleTv = findViewById(R.id.activity_detail_title_tv);
        posterIv = findViewById(R.id.activity_detail_poster_iv);
        releaseDateTv = findViewById(R.id.activity_detail_release_date_tv);
        voteAverageTv = findViewById(R.id.activity_detail_vote_average_tv);
        overviewTv = findViewById(R.id.activity_detail_overview_tv);
    }

    @Override
    public void setupPresenter() {
        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        presenter = new DetailsPresenter(this, movie);
        presenter.start();
    }

    @Override
    public void showDetails(Movie movie) {
        titleTv.setText(movie.getTitle());
        setupPosterIv(movie);
        releaseDateTv.setText(movie.getReleaseDate());
        voteAverageTv.setText(String.format(Locale.getDefault(), AVERAGE_FORMAT,
                movie.getVoteAverage()));
        overviewTv.setText(movie.getOverview());
    }

    private void setupPosterIv(Movie movie) {
        Picasso.with(this)
                .load(movie.getPosterUrl())
                .error(R.drawable.ic_movie_poster_placeholder)
                .into(posterIv);
        posterIv.setContentDescription(movie.getTitle());
    }
}
