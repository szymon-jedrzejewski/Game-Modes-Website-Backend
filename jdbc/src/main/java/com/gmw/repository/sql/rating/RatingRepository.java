package com.gmw.repository.sql.rating;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Rating;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RatingRepository implements Repository<Rating> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    public RatingRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Rating newRating) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new rating!");
            Rating rating = (Rating) persistenceManager.create(newRating);
            Long id = rating.getId();
            LOGGER.debug("New rating with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating rating! " + newRating, e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(Rating rating) throws SqlRepositoryException {
        try {
            persistenceManager.update(rating);
            LOGGER.debug("Rating with id " + rating.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during updating the rating!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, new Rating().getTableName());
            LOGGER.debug("Rating with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during deleting the rating!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<Rating> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(Rating.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}
