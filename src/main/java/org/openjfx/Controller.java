package org.openjfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.model.World;
import org.openjfx.view.View;

import java.io.IOException;

import static javafx.scene.input.KeyCode.*;

/**
 * JavaFX Controller
 */
public class Controller extends Application {
    View view;
    Model model;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new AnchorPane(), 720, 720);
        view = new View(stage, this::handleKeyPress);
        model = new Model();
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