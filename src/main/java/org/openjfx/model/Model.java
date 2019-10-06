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
        world.player.moveUp();
        world.updateWorldGrid(world.player);
        modelHasBeenUpdated();
    }

    public void movePlayerDown() {
        //TODO
        System.out.println("Down");
        world.player.moveDown();
        world.updateWorldGrid(world.player);
        modelHasBeenUpdated();
    }

    public void movePlayerRight() {
        //TODO
        System.out.println("Right");
        world.player.moveRight();
        world.updateWorldGrid(world.player);
        modelHasBeenUpdated();
    }

    public void movePlayerLeft() {
        //TODO
        System.out.println("Left");
        world.player.moveLeft();
        world.updateWorldGrid(world.player);
        modelHasBeenUpdated();

    }

    public void modelHasBeenUpdated() {
        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }

}
