package com.gmw.services.rating.impl;

import com.gmw.coverters.RatingConverter;
import com.gmw.coverters.TOConverter;
import com.gmw.model.Rating;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.rating.DBRatingService;

public class DBRatingServiceImpl extends DBRatingReadServiceImpl implements DBRatingService {
    public DBRatingServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createRating(NewRatingTO newRatingTO) throws ResourceNotCreatedException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();
        TOConverter<NewRatingTO, Rating> converter = new RatingConverter();

        Rating rating = converter.convertToModel(newRatingTO);

        ServiceUtils.create(repository, rating);
    }

    @Override
    public void updateRating(ExistingRatingTO existingRatingTO) throws ResourceNotUpdatedException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();
        TOConverter<NewRatingTO, Rating> converter = new RatingConverter();

        Rating rating = converter.convertToModel(existingRatingTO);
        rating.setId(existingRatingTO.getId());

        ServiceUtils.update(repository, rating);
    }

    @Override
    public void deleteRating(Long ratingId) throws ResourceNotDeletedException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();

        ServiceUtils.delete(repository, ratingId);
    }
}
