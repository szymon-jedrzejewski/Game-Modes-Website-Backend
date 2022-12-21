package com.gmw.coverters;

import com.gmw.model.Rating;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;

public class RatingConverter implements
        ModelConverter<ExistingRatingTO, Rating>,
        TOConverter<NewRatingTO, Rating>{

    @Override
    public ExistingRatingTO convertToTO(Rating rating) {
        return ExistingRatingTO
                .builder()
                .id(rating.getId())
                .modId(rating.getModId())
                .userId(rating.getUserId())
                .rating(rating.getRating())
                .build();
    }

    @Override
    public Rating convertToModel(NewRatingTO newRatingTO) {
        Rating rating = new Rating();
        rating.setRating(newRatingTO.getRating());
        rating.setModId(newRatingTO.getModId());
        rating.setUserId(newRatingTO.getUserId());

        return rating;
    }
}
