package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.openjfx.controller.Controller;

/**
 * JavaFX Controller
 */
public class App extends Application {
    Controller controller;

    @Override
    public void start(Stage stage) {
        controller = new Controller(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}