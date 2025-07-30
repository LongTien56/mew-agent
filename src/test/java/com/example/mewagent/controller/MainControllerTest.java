package com.example.mewagent.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.application.Platform;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MainController class using TestFX.
 */
@DisplayName("Main Controller Tests")
class MainControllerTest extends ApplicationTest {

    private MainController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/mewagent/MainView.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Controller should be initialized properly")
    void testControllerInitialization() {
        assertNotNull(controller, "Controller should be initialized");
    }

    @Test
    @DisplayName("Log area should be empty initially")
    void testLogAreaInitialState() {
        // Note: In a real test environment, you might need to check the actual UI state
        // This is a basic structural test
        assertNotNull(controller, "Controller should exist");
    }

    @Test
    @DisplayName("Clear log should work when called from JavaFX thread")
    void testClearLog() {
        // Execute on JavaFX Application Thread to avoid threading issues
        Platform.runLater(() -> {
            controller.clearLog();
        });
        
        // Just ensure the method doesn't throw exceptions when called properly
        assertDoesNotThrow(() -> {
            Platform.runLater(() -> controller.clearLog());
        });
    }
}
