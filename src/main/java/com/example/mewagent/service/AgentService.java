package com.example.mewagent.service;

import com.example.mewagent.service.interfaces.IAgentService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import com.example.mewagent.repository.ITaskRepository;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AgentService implements IAgentService {

    private final ITaskExecutionService taskExecutionService;
    private static final Pattern PURCHASE_COMMAND_PATTERN = Pattern.compile("buy (.+) from (.+)");
    private final ITaskRepository taskRepository;

    @Inject
    public AgentService(ITaskExecutionService taskExecutionService, ITaskRepository taskRepository) {
        this.taskExecutionService = taskExecutionService;
        this.taskRepository = taskRepository;
    }

    @Override
    public String executeCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "Please enter a command.";
        }

        String cmd = command.trim().toLowerCase();
        Matcher purchaseMatcher = PURCHASE_COMMAND_PATTERN.matcher(cmd);

        if (purchaseMatcher.find()) {
            String item = purchaseMatcher.group(1);
            String website = purchaseMatcher.group(2);
            String url = "https://" + website + ".com";
            Map<String, Object> params = new HashMap<>();
            params.put("item", item);
            params.put("action", "purchase");

            try {
                return taskExecutionService.executeTask("Search for '" + item + "' and add to cart", url, params);
            } catch (Exception e) {
                return "Error: Task execution failed. " + e.getMessage();
            }
        }

        switch (cmd) {
            case "hello":
                return "Hello! I'm Mew Agent";
            case "help":
                return "Available commands include: 'buy [item] from [website]', 'hello', 'help', 'version', 'status'";
            case "version":
                 return "Mew Agent v1.0.0";
            case "status":
                return getStatus();
            default:
                return "Unknown comma nd. Type 'help' for available commands.";
        }
    }

    @Override
    public boolean isReady() {
        return taskExecutionService != null;
    }

    @Override
    public String getStatus() {
        if (isReady()) {
            return "AI Agent is ready. Services initialized successfully.";
        }
        return "AI Agent is not ready.";
    }
}
