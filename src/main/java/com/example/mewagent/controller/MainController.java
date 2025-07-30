package com.example.mewagent.controller;

import com.example.mewagent.service.interfaces.IAgentService;
import javafx.application.Platform;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the main JavaFX interface.
 * This class handles user interactions with the UI elements defined in MainView.fxml.
 */
public class MainController {
    
    @FXML
    private TextArea logArea;
    
    @FXML
    private TextField commandField;
    
    private IAgentService agentService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        agentService = new AgentService();
        appendLog("Mew Agent initialized. Type 'help' for available commands.");
    }

    @FXML
    private void initialize() {
        // Logic khởi tạo có thể đặt ở đây, nó được gọi sau khi FXML được load
        appendLog("Mew Agent đã khởi tạo. Gõ 'help' để xem các lệnh.");
    }
    @FXML
    private void handleExecuteCommand() {
        String command = commandField.getText().trim();
        if (!command.isEmpty()) {
            appendLog("User: " + command);
            
            // Process the command in a background thread to avoid blocking UI
            Platform.runLater(() -> {
                String response = agentService.executeCommand(command);
                appendLog("Agent: " + response);
            });
            
            commandField.clear();
        }
    }
    
    private void appendLog(String message) {
        Platform.runLater(() -> {
            logArea.appendText(message + "\n");
        });
    }
}
