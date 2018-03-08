package com.wojdor.popularmovies.application.discovery;

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

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.MovieViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private final OnItemClickListener onItemClickListener;
    private Context context;
    private List<Movie> movies;

    DiscoveryAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        setupPosterIv(holder.posterIv, movie);
    }

    private void setupPosterIv(ImageView posterIv, Movie movie) {
        Picasso.with(context)
                .load(movie.getPosterUrl())
                .error(R.drawable.ic_movie_poster_placeholder)
                .into(posterIv);
        posterIv.setContentDescription(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies == null ? NO_ITEMS_COUNT : movies.size();
    }

    void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView posterIv;
        private DiscoveryAdapter.OnItemClickListener onItemClickListener;

        MovieViewHolder(View view, DiscoveryAdapter.OnItemClickListener onItemClickListener) {
            super(view);
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
}
