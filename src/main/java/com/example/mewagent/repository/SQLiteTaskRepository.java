package com.example.mewagent.repositories;

import com.example.mewagent.model.orm.Task;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SQLiteTaskRepository implements ITaskRepository {

    private final Dao<Task, String> taskDao;

    public SQLiteTaskRepository() {
        try {
            // Establish the connection source
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:mew_agent.db");

            // Create the DAO for the Task class
            taskDao = DaoManager.createDao(connectionSource, Task.class);

            // Automatically create the "tasks" table if it doesn't exist
            TableUtils.createTableIfNotExists(connectionSource, Task.class);
        } catch (SQLException e) {
            // In a real app, handle this more gracefully
            throw new RuntimeException("Failed to initialize Task repository", e);
        }
    }

    @Override
    public void save(Task task) {
        try {
            // This single line handles both INSERT and UPDATE!
            taskDao.createOrUpdate(task);
        } catch (SQLException e) {
            System.err.println("Error saving task: " + e.getMessage());
        }
    }

    @Override
    public Optional<Task> findById(String id) {
        try {
            Task task = taskDao.queryForId(id);
            return Optional.ofNullable(task);
        } catch (SQLException e) {
            System.err.println("Error finding task by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        try {
            return taskDao.queryForAll();
        } catch (SQLException e) {
            System.err.println("Error finding all tasks: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
