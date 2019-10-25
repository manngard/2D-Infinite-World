package org.openjfx.controller;

import javafx.scene.input.KeyCode;
import org.openjfx.model.Model;
import org.openjfx.model.event.EventListener;
import org.openjfx.view.View;
import org.openjfx.view.ViewEventMessages;

public class PlayerController {
    final Model model;

    PlayerController(Model model, View view) {
        this.model = model;

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
