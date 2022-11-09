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
            LOGGER.error("Can not create new View!");
        }

        throw new SqlRepositoryException();
    }

    @Override
    public void update(View view) {
        persistenceManager.update(view);
        LOGGER.debug("View with id " + view.getId() + " was updated!");
    }

    @Override
    public void delete(Long id) {
        persistenceManager.delete(id, "views");
        LOGGER.debug("View with id " + id + " was deleted!");
    }

    @Override
    public List<View> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(persistable -> (View)persistable).
                    toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!");
        }
        throw new SqlRepositoryException();
    }
}
