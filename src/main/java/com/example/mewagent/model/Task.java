package com.example.mewagent.model;

import java.time.LocalDateTime;
import java.util.Map;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.UUID;
import lombok.AllArgsContructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsContructor;


@Data
@Builder
@AllArgsContructor
@NoArgsContructor
@DatabaseTable(tableName="tasks")
public class Task {
    public enum TaskStatus {
        PENDING, RUNNING, COMPLETED, FAILED, CENCELLED;
    }

    @DatabaseField(id=true)
    private String id;

    @DatabaseField
    private String type;

    @DatabaseField
    private String description;

    @DatabaseField
    private String targetWebsite;

    @DatabaseField
    private TaskStatus status;    

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
