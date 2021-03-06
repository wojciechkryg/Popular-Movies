package com.wojdor.popularmovies.application.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;
import com.wojdor.popularmovies.data.source.device.MoviesDatabase;
import com.wojdor.popularmovies.domain.Movie;
import com.wojdor.popularmovies.domain.Review;
import com.wojdor.popularmovies.domain.Trailer;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String MOVIE_EXTRA = "MOVIE";

    private static final String AVERAGE_FORMAT = "%.1f";
    private static final String SHARE_TYPE = "text/plain";

    @BindView(R.id.activity_detail_title_tv)
    TextView titleTv;
    @BindView(R.id.activity_detail_poster_iv)
    ImageView posterIv;
    @BindView(R.id.activity_detail_favourite_fab)
    FloatingActionButton favouriteFab;
    @BindView(R.id.activity_detail_release_date_tv)
    TextView releaseDateTv;
    @BindView(R.id.activity_detail_vote_average_tv)
    TextView voteAverageTv;
    @BindView(R.id.activity_detail_overview_tv)
    TextView overviewTv;
    @BindView(R.id.activity_detail_trailers_tv)
    TextView trailersTv;
    @BindView(R.id.activity_detail_trailers_rv)
    RecyclerView trailersRv;
    @BindView(R.id.activity_detail_reviews_tv)
    TextView reviewsTv;
    @BindView(R.id.activity_detail_reviews_rv)
    RecyclerView reviewsRv;

    private DetailsContract.Presenter presenter;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private Menu menu;

    @OnClick(R.id.activity_detail_favourite_fab)
    public void onFavouriteFabClick() {
        if (presenter.isMovieFavourite()) {
            presenter.deleteMovieFromFavourites();
        } else {
            presenter.addMovieToFavourites();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setupPresenter();
        setTitle(R.string.details);
        setupFavouriteIcon();
        setupTrailersRv();
        setupReviewsRv();
    }

    private void setupTrailersRv() {
        addTrailerAdapter();
        addSnapping(trailersRv);
    }

    private void addTrailerAdapter() {
        trailerAdapter = new TrailerAdapter(trailer -> presenter.openTrailer(trailer));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        trailersRv.setLayoutManager(layoutManager);
        trailersRv.setAdapter(trailerAdapter);
    }

    private void addSnapping(RecyclerView recyclerView) {
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(recyclerView);
    }

    private void setupReviewsRv() {
        addReviewAdapter();
        addSnapping(reviewsRv);
    }

    private void addReviewAdapter() {
        reviewAdapter = new ReviewAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        reviewsRv.setLayoutManager(layoutManager);
        reviewsRv.setAdapter(reviewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                presenter.shareMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    @Override
    public void setupPresenter() {
        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        MoviesDatabase database = new MoviesDatabase(getContentResolver());
        presenter = new DetailsPresenter(this, database, movie);
        presenter.onAttachView();
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

    @Override
    public void showReviews(List<Review> reviews) {
        toggleLabelVisibility(reviewsTv, reviews.isEmpty());
        reviewAdapter.setReviews(reviews);
    }

    @Override
    public void showTrailers(List<Trailer> trailers) {
        toggleLabelVisibility(trailersTv, trailers.isEmpty());
        trailerAdapter.setTrailers(trailers);
    }

    private void toggleLabelVisibility(TextView tv, boolean shouldBeHidden) {
        if (shouldBeHidden) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showTrailer(String videoUrl) {
        Uri url = Uri.parse(videoUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        if (intent.resolveActivity(getPackageManager()) == null) return;
        startActivity(intent);
    }

    @Override
    public void setupFavouriteIcon() {
        if (presenter.isMovieFavourite()) {
            favouriteFab.setImageResource(R.drawable.ic_favorite);
        } else {
            favouriteFab.setImageResource(R.drawable.ic_unfavorite);
        }
    }

    @Override
    public void showShareMenuItem() {
        menu.findItem(R.id.action_share).setVisible(true);
    }

    @Override
    public void shareTrailer(String url) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType(SHARE_TYPE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }

    private void setupPosterIv(Movie movie) {
        Picasso.with(this)
                .load(movie.getPosterUrl())
                .error(R.drawable.ic_movie_poster_placeholder)
                .into(posterIv);
        posterIv.setContentDescription(movie.getTitle());
    }
}
