package com.hei.petroliere.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection connection;

    private ConnectionDB() {
        try {
            String dbUrl = System.getenv("DB_URL");
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");
            this.connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        if (!isConnectionOpen()) {
            String dbUrl = System.getenv("DB_URL");
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");
            this.connection = DriverManager.getConnection(dbUrl, username, password);
        }
        return connection;
    }
    private boolean isConnectionOpen() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close the database connection", e);
        }
    }
}