package com.wojdor.popularmovies.application.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.domain.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private static final int NO_ITEMS_COUNT = 0;

    private Context context;
    private List<Review> reviews;

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        setupReviewLayout(holder, review);
    }

    private void setupReviewLayout(ReviewViewHolder holder, Review review) {
        holder.authorTv.setText(review.getAuthor());
        holder.reviewTv.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews == null ? NO_ITEMS_COUNT : reviews.size();
    }

    void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        final TextView authorTv;
        final TextView reviewTv;

        ReviewViewHolder(View view) {
            super(view);
            authorTv = view.findViewById(R.id.item_review_author_tv);
            reviewTv = view.findViewById(R.id.item_review_review_tv);
        }
    }
}
