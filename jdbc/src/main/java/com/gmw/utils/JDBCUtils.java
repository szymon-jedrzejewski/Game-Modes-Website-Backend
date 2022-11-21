package com.gmw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private JDBCUtils() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("postgresql://postgres:f7Ul9jjyTq6u8tdfDsHR@containers-us-west-76.railway.app:5908/railway", "postgres", "f7Ul9jjyTq6u8tdfDsHR ");
    }
}
