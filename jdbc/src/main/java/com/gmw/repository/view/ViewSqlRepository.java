package com.gmw.repository.view;


import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
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
            View view = (View) persistenceManager.create(newView);
            return view.getId();
        } catch (SqlPersistenceManagerException e) {
            logger.error("Can not create new View!");
        }

        throw new SqlRepositoryException();
    }

    @Override
    public void update(View view) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<View> find(String query) {
        return null;
    }
}
