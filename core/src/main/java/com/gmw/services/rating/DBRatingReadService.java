package com.gmw.services.rating;

import com.gmw.services.exceptions.ResourceNotFoundException;

public interface DBRatingReadService {
    Double obtainRatingForMod(Long modId) throws ResourceNotFoundException;
}
