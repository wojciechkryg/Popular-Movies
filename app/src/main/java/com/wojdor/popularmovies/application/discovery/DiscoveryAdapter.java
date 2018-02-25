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

    private List<Movie> movies;
    private Context context;

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
        Picasso.with(context).load(movie.getPosterUrl()).into(holder.posterIv);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class DiscoveryAdapterViewHolder extends RecyclerView.ViewHolder {

        public final ImageView posterIv;

        public DiscoveryAdapterViewHolder(View view) {
            super(view);
            posterIv = view.findViewById(R.id.item_movie_poster_iv);
        }
    }
}
