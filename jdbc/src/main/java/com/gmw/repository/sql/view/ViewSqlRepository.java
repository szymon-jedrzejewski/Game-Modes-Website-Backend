package com.gmw.repository.sql.view;


import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public class ViewSqlRepository implements Repository<View> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    @Override
    public Long create(View newView) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new view!");
            View view = (View) persistenceManager.create(newView);
            Long id = view.getId();
            LOGGER.debug("New view with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Can not create new View!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(View view) throws SqlRepositoryException {
        try {
            persistenceManager.update(view);
            LOGGER.debug("View with id " + view.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Can not update the view!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, new View().getTableName());
            LOGGER.debug("View with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Can not delete the view!", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<View> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(View.class::cast).
                    toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}
