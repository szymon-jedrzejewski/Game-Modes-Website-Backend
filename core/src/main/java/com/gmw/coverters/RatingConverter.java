package com.gmw.coverters;

import com.gmw.model.Rating;
import com.gmw.rating.tos.ExistingRatingTO;

public class RatingConverter implements ModelConverter<ExistingRatingTO, Rating> {

    @Override
    public ExistingRatingTO convertToTO(Rating rating) {
        return null;
    }
}
