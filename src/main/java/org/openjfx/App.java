package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.openjfx.controller.Controller;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Controller controller = new Controller(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}