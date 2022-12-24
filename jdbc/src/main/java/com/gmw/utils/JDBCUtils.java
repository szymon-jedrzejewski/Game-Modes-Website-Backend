package com.gmw.utils;


import com.gmw.reader.JsonConfigReader;
import com.gmw.reader.tos.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JDBCUtils {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        DatabaseConfig databaseConfig = JsonConfigReader.readDatabaseConfig();
        config.setJdbcUrl(databaseConfig.url());
        config.setUsername(databaseConfig.user());
        config.setPassword(databaseConfig.password());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
