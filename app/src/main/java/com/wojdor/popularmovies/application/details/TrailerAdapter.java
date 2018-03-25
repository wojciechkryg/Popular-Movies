package com.wojdor.popularmovies.application.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.domain.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private final OnItemClickListener onItemClickListener;
    private Context context;
    private List<Trailer> trailers;

    TrailerAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // TODO: change trailer item layout
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new TrailerViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        setupTrailerIv(holder.trailerIv, trailer);
    }

    private void setupTrailerIv(ImageView trailerIv, Trailer trailer) {
        Picasso.with(context)
                .load(trailer.getThumbnailUrl())
                .error(R.drawable.ic_movie_poster_placeholder)
                .into(trailerIv);
        trailerIv.setContentDescription(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailers == null ? NO_ITEMS_COUNT : trailers.size();
    }

    void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Trailer trailer);
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView trailerIv;
        private TrailerAdapter.OnItemClickListener onItemClickListener;

        TrailerViewHolder(View view, TrailerAdapter.OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            trailerIv = view.findViewById(R.id.item_movie_poster_iv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Trailer trailer = trailers.get(clickedPosition);
            onItemClickListener.onItemClick(trailer);
        }
    }
}
