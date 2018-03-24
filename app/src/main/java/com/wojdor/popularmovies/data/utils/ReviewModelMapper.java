package com.wojdor.popularmovies.data.utils;

import com.wojdor.popularmovies.data.model.ReviewModel;
import com.wojdor.popularmovies.domain.Review;

import java.util.ArrayList;
import java.util.List;

public final class ReviewModelMapper {

    private static ReviewModelMapper instance;

    private ReviewModelMapper() {
    }

    public static ReviewModelMapper getInstance() {
        if (instance == null) {
            instance = new ReviewModelMapper();
        }
        return instance;
    }

    private Review map(ReviewModel model) {
        return new Review(
                model.getAuthor(),
                model.getContent()
        );
    }

    public List<Review> map(List<ReviewModel> reviewModels) {
        List<Review> reviews = new ArrayList<>();
        for (ReviewModel reviewModel : reviewModels) {
            reviews.add(map(reviewModel));
        }
        return reviews;
    }
}
