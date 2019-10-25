package org.openjfx.controller;

import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.model.event.EventListener;
import org.openjfx.view.View;
import org.openjfx.view.ViewEventMessages;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Controller {
    private final Model model;
    private long previousTime = 0;

    public Controller(Stage stage) {
        model = new Model(OpenSimplexAdapter.getInstance());
        View view = new View(stage, model);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long deltaTime = now - previousTime;
                if(deltaTime / 100000 > 500) {
                    model.runTick();
                    previousTime = now;
                }
            }
        }.start();
        model.initModel();
        view.getViewEvent().addListener(new EventListener() {
            @Override
            public void func(Object emsg, Object data) {
                switch((ViewEventMessages) emsg) {
                    case KEYPRESS:
                        handleKeyPress((KeyCode) data);
                        break;
                }
            }
        });
    }

    private void handleKeyPress(KeyCode keyCode) {
        switch(keyCode) {
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
            case E:
                model.playerInteracts();
                break;
            case DIGIT1:
            case DIGIT2:
            case DIGIT3:
            case DIGIT4:
                model.selectInventory(Integer.parseInt(keyCode.getName()));
                break;
        }
    }


}
