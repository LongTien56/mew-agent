package com.example.mewagent.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Execption;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "";
    
    public static Connection getConnection() throw Execption {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        // SQL to create tables if they don't exist
        String createTaskTable = "CREATE TABLE IF NOT EXISTS tasks (id TEXT PRIMARY KEY, type TEXT, description TEXT, status TEXT, result TEXT);";
        String createPatternTable = "CREATE TABLE IF NOT EXISTS patterns (id TEXT PRIMARY KEY, domain TEXT, taskType TEXT, workflowJson TEXT);";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createTaskTable);
            stmt.execute(createPatternTable);
            System.out.println("Database tables initialized successfully.");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}

