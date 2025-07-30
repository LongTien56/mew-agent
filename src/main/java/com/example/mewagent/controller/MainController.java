package com.example.mewagent.controller;

import com.example.mewagent.service.interfaces.IAgentService;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainController {

    @FXML
    private TextArea logArea;

    @FXML
    private TextField commandField;

    private final IAgentService agentService;

    @Inject
    public MainController(IAgentService agentService) {
        this.agentService = agentService;
    }

    @FXML
    private void initialize() {
        appendLog("Mew Agent initialized. Type 'help' for available commands.");
    }

    @FXML
    private void handleExecuteCommand() {
        String command = commandField.getText().trim();
        if (!command.isEmpty()) {
            appendLog("User: " + command);

            // It's good practice to run the service call on a background thread
            // to keep the UI responsive, but Platform.runLater is for UI updates.
            // For now, this is okay.
            String response = agentService.executeCommand(command);
            appendLog("Agent: " + response);

            commandField.clear();
        }
    }
    
    // This is a new helper method from your test file.
    public void clearLog() {
        Platform.runLater(logArea::clear);
    }

    private void appendLog(String message) {
        // Ensure UI updates happen on the JavaFX Application Thread.
        Platform.runLater(() -> {
            logArea.appendText(message + "\n");
        });
    }
}
