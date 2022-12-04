package com.gmw.repository.sql.user;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.User;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserRepository implements Repository<User> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    public UserRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(User newUser) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new user!");
            User user = (User) persistenceManager.create(newUser);
            Long id = user.getId();
            LOGGER.debug("New user with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating user! " + newUser, e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(User user) throws SqlRepositoryException {
        try {
            persistenceManager.update(user);
            LOGGER.debug("User with id " + user.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during updating the user!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, "users");
            LOGGER.debug("User with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during deleting the user!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<User> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(User.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}
