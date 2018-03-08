package com.wojdor.popularmovies.application.discovery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

public class DiscoveryAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private Context context;
    private final OnItemClickListener onItemClickListener;
    private List<Movie> movies;

    public DiscoveryAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view, movies, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        setupPosterIv(holder.posterIv, movie);
    }

    private void setupPosterIv(ImageView posterIv, Movie movie) {
        Picasso.with(context).load(movie.getPosterUrl()).into(posterIv);
        posterIv.setContentDescription(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies == null ? NO_ITEMS_COUNT : movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Movie movie);
    }
}
