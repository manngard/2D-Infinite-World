package org.openjfx.controller;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.view.View;

public class Controller {
    View view;
    Model model;

    public Controller(Stage stage) {
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
            case SPACE:
                model.playerAttacks();
                break;
        }
    }
}
