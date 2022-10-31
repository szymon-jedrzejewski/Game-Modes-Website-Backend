package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.persistence.Persistable;
import com.gmw.persistence.QuerySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class SqlSafePersistenceManager extends SqlPersistenceManager{

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "There was attempt of sql injection!";

    public SqlSafePersistenceManager(Connection connection) {
        super(connection);
    }

    @Override
    public Persistable create(Persistable persistable) throws SqlPersistenceManagerException {
        if (!SqlInjectionChecker.check(persistable))
        {
            return super.create(persistable);
        } else {
            LOGGER.warn(MESSAGE);
            return null;
        }
    }

    @Override
    public void update(Persistable persistable) {
        if (!SqlInjectionChecker.check(persistable))
        {
            super.update(persistable);
        } else {
            LOGGER.warn(MESSAGE);
        }
    }

    @Override
    public void delete(Long id, String tableName) {
        super.delete(id, tableName);
    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) throws SqlPersistenceManagerException {
        if (!SqlInjectionChecker.check(querySpec))
        {
            return super.find(querySpec);
        } else {
            LOGGER.warn(MESSAGE);
            return null;
        }
    }
}
