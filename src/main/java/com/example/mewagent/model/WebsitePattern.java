package com.example.mewagent.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents website-specific knowledge and patterns learned by the AI agent.
 */
public class WebsitePattern {
    private String id;
    private String websiteDomain;
    private String taskType;
    private String patternName;
    private Map<String, String> selectors; // CSS selectors for elements
    private Map<String, String> workflows; // Step-by-step workflows
    private Map<String, Object> metadata;
    private int successCount;
    private int failureCount;
    private LocalDateTime lastUsed;
    private LocalDateTime createdAt;
    private boolean isActive;

    // Constructors
    public WebsitePattern() {
        this.createdAt = LocalDateTime.now();
        this.lastUsed = LocalDateTime.now();
        this.isActive = true;
        this.successCount = 0;
        this.failureCount = 0;
    }

    public WebsitePattern(String websiteDomain, String taskType, String patternName) {
        this();
        this.websiteDomain = websiteDomain;
        this.taskType = taskType;
        this.patternName = patternName;
    }

    // Methods for tracking success/failure
    public void recordSuccess() {
        this.successCount++;
        this.lastUsed = LocalDateTime.now();
    }

    public void recordFailure() {
        this.failureCount++;
        this.lastUsed = LocalDateTime.now();
    }

    public double getSuccessRate() {
        int total = successCount + failureCount;
        return total > 0 ? (double) successCount / total : 0.0;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getWebsiteDomain() { return websiteDomain; }
    public void setWebsiteDomain(String websiteDomain) { this.websiteDomain = websiteDomain; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getPatternName() { return patternName; }
    public void setPatternName(String patternName) { this.patternName = patternName; }

    public Map<String, String> getSelectors() { return selectors; }
    public void setSelectors(Map<String, String> selectors) { this.selectors = selectors; }

    public Map<String, String> getWorkflows() { return workflows; }
    public void setWorkflows(Map<String, String> workflows) { this.workflows = workflows; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getFailureCount() { return failureCount; }
    public void setFailureCount(int failureCount) { this.failureCount = failureCount; }

    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
