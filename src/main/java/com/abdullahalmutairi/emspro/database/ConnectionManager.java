package com.abdullahalmutairi.emspro.database;

import com.abdullahalmutairi.emspro.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DatabaseConfig.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found: " + DatabaseConfig.JDBC_DRIVER, e);
        }
        return DriverManager.getConnection(DatabaseConfig.DATABASE_URL);
    }
}
