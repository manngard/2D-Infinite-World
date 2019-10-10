package org.openjfx.model;


import javafx.animation.AnimationTimer;
import org.openjfx.utils.event.Event;

import java.util.ArrayList;

public class Model {


    private World world;
    private final int maxDistance = 10;
    private long previousTime = 0;

    public Event<EventMessage> hasUpdateEvent;

    public Model() {
        world = new World();
        hasUpdateEvent = new Event<>();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long deltaTime = now - previousTime;
                System.out.println(deltaTime / 1000000);
                if(deltaTime / 100000 > 500) {
                    world.moveMobs();
                    modelHasBeenUpdated();
                    previousTime = now;
                }
            }
        }.start();

    }

    public void movePlayerUp() {
        System.out.println("Up");
        //TODO
        world.player.move(Movable.Direction.UP);
        modelHasBeenUpdated();
    }

    public void movePlayerDown() {
        //TODO
        System.out.println("Down");
        world.player.move(Movable.Direction.DOWN);
        modelHasBeenUpdated();
    }

    public void movePlayerRight() {
        //TODO
        System.out.println("Right");
        world.player.move(Movable.Direction.RIGHT);
        modelHasBeenUpdated();
    }

    public void movePlayerLeft() {
        //TODO
        System.out.println("Left");
        world.player.move(Movable.Direction.LEFT);
        modelHasBeenUpdated();

    }

    public void modelHasBeenUpdated() {

        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }

    public void playerAttacks(){

        world.attackHit(world.player, world.playerAttacks(world.player, world.getEnemies()));

    }
    public void playerInteracts(){
        for (Chest chest : world.getChests()){
            if (world.isEntityWithinDistance(chest)){
                for (int i = 0; i<4; i++)
                    world.player.setItem(chest.getItem(i),i);
            }
        }
    }


}

