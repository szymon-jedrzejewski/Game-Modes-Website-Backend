package com.gmw.repository.sql.fieldvalue;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.FieldValue;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FieldValueSqlRepository implements Repository<FieldValue> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;
    public FieldValueSqlRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(FieldValue newFieldValue) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new field value!");
            FieldValue fieldValue = (FieldValue) persistenceManager.create(newFieldValue);
            Long id = fieldValue.getId();
            LOGGER.debug("New field value with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating field value! " + newFieldValue, e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(FieldValue fieldValue) throws SqlRepositoryException {
        try {
            persistenceManager.update(fieldValue);
            LOGGER.debug("Field value with id " + fieldValue.getId() + " was updated!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during updating the field value! ", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void delete(Long id) throws SqlRepositoryException {
        try {
            persistenceManager.delete(id, new FieldValue().getTableName());
            LOGGER.debug("Field value with id " + id + " was deleted!");
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during deleting the field value! ", e);
            throw new SqlRepositoryException();
        }
    }

    @Override
    public List<FieldValue> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(FieldValue.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
            throw new SqlRepositoryException();
        }
    }
}
