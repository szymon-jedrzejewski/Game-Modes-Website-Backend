package com.gmw.services.rating;

import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBRatingReadService {
    Double obtainRatingForMod(Long modId);

    List<Long> obtainRatingsIdsByModId(Long modId) throws ResourceNotFoundException;

    Long obtainUserIdByRatingId(Long id) throws ResourceNotFoundException;
}
