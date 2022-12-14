package com.gmw.rating.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingRatingTO extends NewRatingTO {
    private Long id;

    @Builder
    public ExistingRatingTO(Long userId, Long modId, Integer rating, Long id) {
        super(userId, modId, rating);
        this.id = id;
    }
}
