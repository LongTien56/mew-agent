package com.example.mewagent;

import com.example.mewagent.config.AppModule; // Make sure you have created this file
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static final String WINDOW_TITLE = "Mew Agent";
    private static final double DEFAULT_WIDTH = 800;
    private static final double DEFAULT_HEIGHT = 600;

    private Injector injector;

    @Override
    public void init() throws Exception {
        super.init();
        // Create the Guice injector with our application's configuration module.
        // This is the "factory" that will build our objects.
        this.injector = Guice.createInjector(new AppModule());
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Starting Mew Agent application");

            URL fxmlLocation = getClass().getResource("/com/example/mewagent/MainView.fxml");
            if (fxmlLocation == null) {
                logger.error("Cannot find MainView.fxml file. Make sure it's in src/main/resources/com/example/mewagent/");
                showErrorAndExit("FXML file not found");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            loader.setControllerFactory(injector::getInstance);

            Parent root = loader.load();

            Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);

            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            primaryStage.setOnCloseRequest(event -> {
                logger.info("Application closing");
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

    private void showErrorAndExit(String message) {
        System.err.println("Error: " + message);
        System.exit(1);
    }

    public static void main(String[] args) {
        logger.info("Launching Mew Agent with args: {}", java.util.Arrays.toString(args));
        launch(args);
    }
}
