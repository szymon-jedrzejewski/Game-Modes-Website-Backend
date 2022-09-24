package com.gmw.repository.field;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Field;
import com.gmw.persistence.PersistenceManager;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FieldSqlRepository implements Repository<Field> {
    private static final Logger logger = LogManager.getLogger();
    private final PersistenceManager persistenceManager;
    public FieldSqlRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Field newField) throws SqlRepositoryException {

        try {
            Field field = (Field) persistenceManager.create(newField);
            return field.getId();
        } catch (SqlPersistenceManagerException e) {
            logger.error("Error during creating field! " + newField);
        }
        throw new SqlRepositoryException();
    }

    @Override
    public void update(Field field) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Field> find(String query) {
        return null;
    }
}
