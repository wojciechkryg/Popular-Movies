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

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DiscoveryAdapterViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private Context context;
    private final ItemClickListener itemClickListener;
    private List<Movie> movies;

    public DiscoveryAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public DiscoveryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new DiscoveryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscoveryAdapterViewHolder holder, int position) {
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

    public interface ItemClickListener {

        void onItemClick(Movie movie);
    }

    public class DiscoveryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView posterIv;

        public DiscoveryAdapterViewHolder(View view) {
            super(view);
            posterIv = view.findViewById(R.id.item_movie_poster_iv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Movie movie = movies.get(clickedPosition);
            itemClickListener.onItemClick(movie);
        }
    }
}
