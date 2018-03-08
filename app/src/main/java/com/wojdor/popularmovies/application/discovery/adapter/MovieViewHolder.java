package com.wojdor.popularmovies.application.discovery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.domain.Movie;

import java.util.List;

class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    final ImageView posterIv;
    private final List<Movie> movies;
    private DiscoveryAdapter.OnItemClickListener onItemClickListener;

    MovieViewHolder(View view, List<Movie> movies,
                    DiscoveryAdapter.OnItemClickListener onItemClickListener) {
        super(view);
        this.movies = movies;
        this.onItemClickListener = onItemClickListener;
        posterIv = view.findViewById(R.id.item_movie_poster_iv);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        Movie movie = movies.get(clickedPosition);
        onItemClickListener.onItemClick(movie);
    }
}
