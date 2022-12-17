package com.gmw.services.rating;

import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBRatingService extends DBRatingReadService {

    void createRating(NewRatingTO newRatingTO) throws ResourceNotCreatedException;
    void updateRating(ExistingRatingTO existingRatingTO) throws ResourceNotUpdatedException;
    void deleteRating(Long ratingId) throws ResourceNotDeletedException;
}
