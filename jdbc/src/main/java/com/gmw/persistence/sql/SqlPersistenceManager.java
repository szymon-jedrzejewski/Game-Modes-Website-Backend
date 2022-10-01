package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlQueryGeneratorException;
import com.gmw.persistence.Persistable;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

@AllArgsConstructor
public class SqlPersistenceManager implements PersistenceManager {
    private static final Logger logger = LogManager.getLogger();

    private final Connection connection;

    @Override
    public Persistable create(Persistable persistable) throws SqlPersistenceManagerException {
        try {
            String query = SqlQueryGenerator.generateCreateQuery(persistable);

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

        } catch (SqlQueryGeneratorException e) {
            logger.error("Error during adding new record to database! " + persistable);
            throw new SqlPersistenceManagerException();
        }
        return persistable;
    }

    @Override
    public Long update(Persistable persistable) {
        return null;
    }

    @Override
    public void delete(Long id, Class<?> clazz) {

    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) {
        return null;
    }
}
