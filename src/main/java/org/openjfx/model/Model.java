package org.openjfx.model;

import org.openjfx.utils.event.Event;

public class Model {


    private World world;

    public Event<EventMessage> hasUpdateEvent;

    public Model() {
        world = new World();
        hasUpdateEvent = new Event<>();

    }

    public void movePlayerUp() {
        System.out.println("Up");
        //TODO
        world.player.move(Movable.Direction.UP);
        //modelHasBeenUpdated();
    }

    public void movePlayerDown() {
        //TODO
        System.out.println("Down");
        world.player.move(Movable.Direction.DOWN);
        //modelHasBeenUpdated();
    }

    public void movePlayerRight() {
        //TODO
        System.out.println("Right");
        world.player.move(Movable.Direction.RIGHT);
        //modelHasBeenUpdated();
    }

    public void movePlayerLeft() {
        //TODO
        System.out.println("Left");
        world.player.move(Movable.Direction.LEFT);
        //modelHasBeenUpdated();

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


    public void moveMobsInWorld(){
        world.moveMobs();
    }

}

