package com.example.mewagent.repository;

import com.example.mewagent.model.Task; 
import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    void save(Task task);
    Optional<Task> findById(String id);
    List<Task> findAll();
}