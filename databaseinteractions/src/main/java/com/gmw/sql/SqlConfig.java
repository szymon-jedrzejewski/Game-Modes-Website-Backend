package com.gmw.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConfig {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("","", "");
    }
}
