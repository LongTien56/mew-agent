package com.example.mewagent.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "tasks")
public class Task {

    public enum TaskStatus {
        PENDING, RUNNING, COMPLETED, FAILED, CANCELLED // Corrected typo
    }

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String type;

    @DatabaseField
    private String description;

    @DatabaseField
    private String targetWebsite;

    @DatabaseField
    private TaskStatus status;

    // This field will not be saved to the database, which is correct for transient data
    @DatabaseField(persisted = false)
    private Map<String, Object> parameters;

    @DatabaseField
    private LocalDateTime createdAt;

    @DatabaseField
    private LocalDateTime updatedAt;

    @DatabaseField
    private String result;

    @DatabaseField
    private String errorMessage;
}