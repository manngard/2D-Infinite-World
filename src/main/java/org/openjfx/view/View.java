package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View {
    private Stage stage;

    public View(Stage stage, EventHandler<KeyEvent> handler) {
        this.stage = stage;
        Scene scene = new Scene(new AnchorPane(), 700, 700);
        stage.setScene(scene);
        stage.show();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, handler);
    }


}
