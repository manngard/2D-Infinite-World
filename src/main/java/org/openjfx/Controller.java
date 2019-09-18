package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.model.World;
import org.openjfx.view.View;

import java.io.IOException;

/**
 * JavaFX Controller
 */
public class Controller extends Application {
    View view;
    World model;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new AnchorPane(), 720, 720);
        view = new View(stage);
        model = new World();
    }

    public static void main(String[] args) {
        launch();
    }

}