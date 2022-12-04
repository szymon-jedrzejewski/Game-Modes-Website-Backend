package com.gmw.repository.sql.field;

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
    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;
    public FieldSqlRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Field newField) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new field!");
            Field field = (Field) persistenceManager.create(newField);
            Long id = field.getId();
            LOGGER.debug("New field with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating field! " + newField, e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(Field field) throws SqlRepositoryException {
        try {
            persistenceManager.update(field);
            LOGGER.debug("Field with id " + field.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during updating the field! ", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, "fields");
            LOGGER.debug("Field with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during deleting the field! ", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<Field> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(Field.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}
