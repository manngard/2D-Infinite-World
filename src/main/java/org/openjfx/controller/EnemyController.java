package org.openjfx.controller;

import javafx.animation.AnimationTimer;
import org.openjfx.model.Model;

public class EnemyController {
    private long previousTime = 0;

    EnemyController(Model model) {
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
    }
}
