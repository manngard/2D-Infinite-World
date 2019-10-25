package org.openjfx.controller;

import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.view.View;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Controller component in MVC structure.
  Used by: App
  Uses:
  */

public class Controller {
    private final Model model;

    public Controller(Stage stage) {
        model = new Model(OpenSimplexAdapter.getInstance());
        View view = new View(stage, model);

        new EnemyController(model);
        new PlayerController(model, view);

        model.initModel();
    }
}
