package com.example.mewagent.service;
import com.example.mewagent.service.interfaces.IAgentService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import com.google.inject.Inject;
/**
 * Simple agent service for basic command processing.
 * This provides a simplified interface for user commands.
 */
public class AgentService {

    private final ITaskExecutionService taskExecutionService;
    private final IAgentService agentService;

    @Inject
    public AgentService(ITaskExecutionService taskExecutionService, IAgentService agentService){
        this.taskExecutionService =  taskExecutionService;
        this.agentService = agentService;
    }

    @Override
    public String executeCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "Please enter a command.";
        }
        
        String cmd = command.trim().toLowerCase();
        
        switch (cmd) {
            case "hello":
                return "Hello! I'm your Mew Agent assistant.";
            case "help":
                return "Available commands: hello, status, help, test";
            case "status":
                return "Agent is running and ready for commands.";
            case "test":
                return "Test successful! Agent is working correctly.";
            default:
                return "Unknown command: " + command + ". Type 'help' for available commands.";
        }
    }
    @Override
    public boolean isReady(){
        return true;
    }
    @Override
    public String getStatus(){
        return "AI agent is ready";
    }

}
