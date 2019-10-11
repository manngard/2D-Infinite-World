package org.openjfx.model;


import javafx.animation.AnimationTimer;
import org.openjfx.model.noise.NoiseGenerator;
import org.openjfx.utils.event.Event;

public class Model {


    private World world;
    private final int maxDistance = 10;
    private long previousTime = 0;

    public Event<EventMessage> hasUpdateEvent;

    public Model() {
        this(null);
    }

    public Model(NoiseGenerator noiseGenerator) {
        world = new World(noiseGenerator);
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
        world.player.move(Movable.Direction.UP);
        world.updateWorldGrid();
        modelHasBeenUpdated();
    }

    public void movePlayerDown() {
        System.out.println("Down");
        world.player.move(Movable.Direction.DOWN);
        world.updateWorldGrid();
        modelHasBeenUpdated();
    }

    public void movePlayerRight() {
        System.out.println("Right");
        world.player.move(Movable.Direction.RIGHT);
        world.updateWorldGrid();
        modelHasBeenUpdated();
    }

    public void movePlayerLeft() {
        System.out.println("Left");
        world.player.move(Movable.Direction.LEFT);
        world.updateWorldGrid();
        modelHasBeenUpdated();

    }

    public void modelHasBeenUpdated() {

        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }

    public void playerAttacks(){

        world.attackHit(world.player, world.playerAttacks(world.player, world.getEnemies()));
        modelHasBeenUpdated();

    }
    public void playerInteracts(){
        for (Chest chest : world.getChests()){
            if (world.isEntityWithinDistance(chest) & world.inSight(world.getPlayer(),chest)){
                for (int i = 0; i<4; i++)
                    world.player.setItem(chest.getItem(i),i);
            }
        }
        modelHasBeenUpdated();
    }


}

