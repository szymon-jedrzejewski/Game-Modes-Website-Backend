package com.gmw.services.testutilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDbUtilities {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String jdbcURL = "jdbc:h2:~/test";
    private static final String jdbcUsername = "sa";
    private static final String jdbcPassword = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            LOGGER.error("Cannot create connection to in-memory database!", e);
        }
        return connection;
    }

    public static void initializeDatabase(ServiceType serviceType) {
        try {
            try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
                for (String query : TestDbConstants.getQueries(serviceType))
                {
                    statement.execute(query);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
