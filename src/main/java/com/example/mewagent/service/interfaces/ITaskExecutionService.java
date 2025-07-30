package com.example.mewagent.service.interfaces;

import java.util.Map;

public interface ITaskExecutionService {
    String executeTask(String taskDescription, String targetUrl, Map<String, Object> parameters);
}
