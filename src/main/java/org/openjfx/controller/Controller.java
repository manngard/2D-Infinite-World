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
    private View view;
    private Model model;
    private double previousTime = 0;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    public Controller(Stage stage) {
        model = new Model(OpenSimplexAdapter.getInstance());
        view = new View(stage, model.hasUpdateEvent);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double deltaTime = (now - previousTime) / (1000000/60);
                System.out.println("FPS: " + deltaTime);
                /*if(deltaTime / 100000 > 500) {
                    model.moveMobsInWorld();
                    movePlayer();
                    model.mobsAttack();
                    model.modelHasBeenUpdated();
                    previousTime = now;
                }*/

                if(deltaTime >= (2)){   //Update world every 30/seconds
                    model.moveMobsInWorld();
                    movePlayer();
                    model.mobsAttack();
                    model.modelHasBeenUpdated();

                }
                if(deltaTime >= 1){ //Render world 60/seconds
                    model.modelHasBeenUpdated();
                    //System.out.println(deltaTime);
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
                    case KEYRELEASED:
                        handleKeyReleased((KeyCode) data);
                        break;
                }
            }
        });
    }

    private void handleKeyPress(KeyCode keyCode) {
        switch(keyCode) {
            case UP:
                if(!moveUp){
                    model.movePlayerUp();
                }
                moveUp = true;
                break;
            case DOWN:
                if(!moveDown){
                    model.movePlayerDown();
                }
                moveDown = true;
                //model.movePlayerDown();
                break;
            case RIGHT:
                if(!moveRight){
                    model.movePlayerRight();
                }
                moveRight = true;
                //model.movePlayerRight();
                break;
            case LEFT:
                if(!moveLeft){
                    model.movePlayerLeft();
                }
                moveLeft = true;
                //model.movePlayerLeft();
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

    private void handleKeyReleased(KeyCode keyCode) {
        switch(keyCode) {
            case UP:
                moveUp = false;
                break;
            case DOWN:
                moveDown = false;
                break;
            case RIGHT:
                moveRight = false;
                break;
            case LEFT:
                moveLeft = false;
                break;
        }

    }

    public void movePlayer(){
        if(moveUp){
            model.movePlayerUp();
        }
        if(moveDown){
            model.movePlayerDown();
        }
        if(moveRight){
            model.movePlayerRight();
        }
        if(moveLeft){
            model.movePlayerLeft();
        }
    }


}
