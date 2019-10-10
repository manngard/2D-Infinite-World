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
                if(deltaTime / 1000000 > 500) {
                    moveMobs();
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

    public boolean isMobWithinDistance(Enemy enemy){

        if(world.distance(world.player, enemy) <= maxDistance)
            return true;
        return false;
    }

    public void playerAttacks(){

        world.playerAttacks();

    }

    public void moveMobs(){
        //int rand = (int)Math.ceil(Math.random() * 2);

        for(Enemy enemy: world.getEnemies()){
            if(isMobWithinDistance(enemy)){
                if(world.player.xcoord < enemy.xcoord){
                    enemy.move(Movable.Direction.LEFT);
                }
                else if(world.player.xcoord > enemy.xcoord){
                    enemy.move(Movable.Direction.RIGHT);
                }
                else if(world.player.ycoord < enemy.ycoord){
                    enemy.move(Movable.Direction.UP);
                }
                else if(world.player.ycoord > enemy.ycoord){
                    enemy.move(Movable.Direction.DOWN);
                }
            }
        }

    }

}
