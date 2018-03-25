package com.wojdor.popularmovies.data.utils;

import com.wojdor.popularmovies.data.model.TrailerModel;
import com.wojdor.popularmovies.domain.Trailer;

import java.util.ArrayList;
import java.util.List;

public final class TrailerModelMapper {

    private static TrailerModelMapper instance;

    private TrailerModelMapper() {
    }

    public static TrailerModelMapper getInstance() {
        if (instance == null) {
            instance = new TrailerModelMapper();
        }
        return instance;
    }

    private Trailer map(TrailerModel model) {
        return new Trailer(
                UrlUtils.getInstance().getTrailerVideoUrl(model.getKey()),
                UrlUtils.getInstance().getTrailerThumbnailUrl(model.getKey()),
                model.getName()
        );
    }

    public List<Trailer> map(List<TrailerModel> trailerModels) {
        List<Trailer> trailers = new ArrayList<>();
        for (TrailerModel trailerModel : trailerModels) {
            trailers.add(map(trailerModel));
        }
        return trailers;
    }
}
