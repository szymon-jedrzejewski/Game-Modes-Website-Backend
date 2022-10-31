package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlQueryUtilityException;
import com.gmw.persistence.Persistable;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

@AllArgsConstructor
public class SqlPersistenceManager implements PersistenceManager {
    private static final Logger logger = LogManager.getLogger();

    private final Connection connection;

    @Override
    public Persistable create(Persistable persistable) throws SqlPersistenceManagerException {
        try {
            String query = SqlQueryUtility.generateCreateQuery(persistable);

            logger.debug("Executing query: " + query);

            try {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        persistable.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            } catch (SQLException e) {
                logger.error("SQLException: " + e.getMessage());
            }

        } catch (SqlQueryUtilityException e) {
            logger.error("Error during adding new record to database! " + persistable);
            throw new SqlPersistenceManagerException();
        }
        return persistable;
    }

    @Override
    public void update(Persistable persistable) {
        try {
            String query = SqlQueryUtility.generateUpdateQuery(persistable);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException | SqlQueryUtilityException e) {
            logger.error("SQLException: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id, String tableName) {
        try {
            String query = SqlQueryUtility.generateDeleteQuery(tableName, id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) throws SqlPersistenceManagerException {
        try {
            String query = SqlQueryUtility.generateFindQuery(querySpec);
            logger.debug("Find query manager: " + query);
            PreparedStatement statement = connection.prepareStatement(query);

            return SqlQueryUtility.resultSetToPersistable(statement, querySpec);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            logger.error("Error during searching for values! " + e.getMessage());
        }

        throw new SqlPersistenceManagerException();
    }
}
