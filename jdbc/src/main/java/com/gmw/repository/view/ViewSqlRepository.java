package com.gmw.repository.view;


import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.View;
import com.gmw.persistence.Persistable;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public class ViewSqlRepository implements Repository<View> {
    private static final Logger logger = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    @Override
    public Long create(View newView) throws SqlRepositoryException {
        try {
            logger.debug("Creating new view!");
            View view = (View) persistenceManager.create(newView);
            Long id = view.getId();
            logger.debug("New view with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            logger.error("Can not create new View!");
        }

        throw new SqlRepositoryException();
    }

    @Override
    public void update(View view) {
        Long id = persistenceManager.update(view);
        logger.debug("View with id " + id + " was updated!");
    }

    @Override
    public void delete(Long id) {
        persistenceManager.delete(id, View.class);
        logger.debug("View with id " + id + " was deleted!");
    }

    @Override
    public List<View> find(QuerySpec querySpec) {
        return persistenceManager
                .find(querySpec)
                .stream()
                .map(persistable -> (View)persistable).
                toList();
    }
}
