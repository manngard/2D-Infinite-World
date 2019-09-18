package org.openjfx.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View {
    private Stage stage;

    public View(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(new AnchorPane(), 700, 700);
        stage.setScene(scene);
        stage.show();
    }
}
