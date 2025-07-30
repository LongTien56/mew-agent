package com.example.mewagent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Main application class for Mew Agent.
 * This class extends JavaFX Application and serves as the entry point for the GUI application.
 */
public class MainApp extends Application {
    
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static final String WINDOW_TITLE = "Mew Agent";
    private static final double DEFAULT_WIDTH = 800;
    private static final double DEFAULT_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Starting Mew Agent application");
            
            // Load FXML file
            URL fxmlLocation = getClass().getResource("/com/example/mewagent/MainView.fxml");
            if (fxmlLocation == null) {
                logger.error("Cannot find MainView.fxml file. Make sure it's in src/main/resources/com/example/mewagent/");
                showErrorAndExit("FXML file not found");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            
            // Create and configure scene
            Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            
            // Configure primary stage
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            
            // Set application icon if available
            // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            
            // Handle close request
            primaryStage.setOnCloseRequest(event -> {
                logger.info("Application closing");
                // Add any cleanup code here
            });
            
            primaryStage.show();
            logger.info("Application started successfully");
            
        } catch (IOException e) {
            logger.error("Failed to load FXML file", e);
            showErrorAndExit("Failed to load application interface");
        } catch (Exception e) {
            logger.error("Unexpected error during application startup", e);
            showErrorAndExit("Unexpected error occurred");
        }
    }

    /**
     * Shows an error message and exits the application.
     * 
     * @param message The error message to display
     */
    private void showErrorAndExit(String message) {
        System.err.println("Error: " + message);
        System.exit(1);
    }

    /**
     * Application entry point.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        logger.info("Launching Mew Agent with args: {}", java.util.Arrays.toString(args));
        launch(args);
    }
}
