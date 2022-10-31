package com.gmw.repository.field;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Field;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
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
            logger.debug("Creating new field!");
            Field field = (Field) persistenceManager.create(newField);
            Long id = field.getId();
            logger.debug("New field with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            logger.error("Error during creating field! " + newField);
        }
        throw new SqlRepositoryException();
    }

    @Override
    public void update(Field field) {
        Long id = persistenceManager.update(field);
        logger.debug("Field with id " + id + " was updated!");
    }

    @Override
    public void delete(Long id) {
        persistenceManager.delete(id, Field.class);
        logger.debug("Field with id " + id + " was deleted!");
    }

    @Override
    public List<Field> find(QuerySpec querySpec) {
        return persistenceManager
                .find(querySpec)
                .stream()
                .map(persistable -> (Field)persistable)
                .toList();
    }
}
