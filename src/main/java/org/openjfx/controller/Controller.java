package org.openjfx.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.openjfx.model.Model;
import org.openjfx.utils.event.EventListener;
import org.openjfx.view.View;
import org.openjfx.view.ViewEventMessages;


public class Controller {

    View view;
    Model model;
    private long previousTime = 0;

    public Controller(Stage stage) {
        model = new Model(OpenSimplexAdapter.getInstance());
        view = new View(stage, model.hasUpdateEvent);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long deltaTime = now - previousTime;
                System.out.println(deltaTime / 1000000);
                if(deltaTime / 100000 > 500) {
                    model.moveMobsInWorld();
                    model.modelHasBeenUpdated();
                    previousTime = now;
                }
            }
        }.start();
        model.modelHasBeenUpdated();
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
                model.selectInventory(Integer.parseInt(keyEvent.getCode().getName()));
                break;
        }
    }


}
