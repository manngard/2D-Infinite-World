package org.openjfx;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.view.View;

import java.io.IOException;

/**
 * JavaFX Controller
 */
public class Controller extends Application {
    View view;
    Model model;

    @Override
    public void start(Stage stage) throws IOException {
        model = new Model();
        view = new View(stage, this::handleKeyPress, model.hasUpdateEvent);
        model.modelHasBeenUpdated();
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        switch(keyEvent.getCode()) {
            case UP:
                model.movePlayerUp();
                break;
            case DOWN:
                model.movePlayerDown();
                break;
            case RIGHT:
                model.movePlayerRight();
                break;
            case LEFT:
                model.movePlayerLeft();
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}