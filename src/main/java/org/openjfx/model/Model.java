package org.openjfx.model;

import org.openjfx.utils.event.Event;

public class Model {

    private World world;
    private final int maxDistance = 10;

    public Event<EventMessage> hasUpdateEvent;

    public Model() {
        world = new World();
        hasUpdateEvent = new Event<>();
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

}
