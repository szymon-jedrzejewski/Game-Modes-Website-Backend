package com.gmw.utils;


import com.gmw.reader.JsonConfigReader;
import com.gmw.reader.tos.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private JDBCUtils() {
    }

    public static Connection getConnection() throws SQLException {
        DatabaseConfig databaseConfig = JsonConfigReader.readDatabaseConfig();
        return DriverManager.getConnection(databaseConfig.url(), databaseConfig.user(), databaseConfig.password());
    }
}
