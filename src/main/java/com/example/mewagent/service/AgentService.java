package com.example.mewagent.service;

/**
 * Simple agent service for basic command processing.
 * This provides a simplified interface for user commands.
 */
public class AgentService {
    
    /**
     * Execute a user command and return a response.
     * @param command the command string from user input
     * @return response message
     */
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
}
