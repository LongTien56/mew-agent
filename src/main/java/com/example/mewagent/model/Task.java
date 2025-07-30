package com.example.mewagent.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a task that the AI agent can execute.
 */
public class Task {
    private String id;
    private String type; // "web_purchase", "task_update", "data_extraction", etc.
    private String description;
    private String targetWebsite;
    private Map<String, Object> parameters;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String result;
    private String errorMessage;

    // Constructors
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = TaskStatus.PENDING;
    }

    public Task(String type, String description, String targetWebsite, Map<String, Object> parameters) {
        this();
        this.type = type;
        this.description = description;
        this.targetWebsite = targetWebsite;
        this.parameters = parameters;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTargetWebsite() { return targetWebsite; }
    public void setTargetWebsite(String targetWebsite) { this.targetWebsite = targetWebsite; }

    public Map<String, Object> getParameters() { return parameters; }
    public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { 
        this.status = status; 
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public enum TaskStatus {
        PENDING, RUNNING, COMPLETED, FAILED, CANCELLED
    }
}
