package org.openjfx.model;

import org.openjfx.utils.Event;

public class Model {
    private World world;
    public Event modelUpdateEvent;

    public Model() {
        world = new World();
        modelUpdateEvent = new Event();
    }

    public void movePlayerUp() {
        System.out.println("Up");
        //TODO
    }

    public void movePlayerDown() {
        //TODO
        System.out.println("Down");
    }

    public void movePlayerRight() {
        //TODO
        System.out.println("Right");
    }

    public void movePlayerLeft() {
        //TODO
        System.out.println("Left");
    }

    private void modelHasBeenUpdated() {
        modelUpdateEvent.dispatch(Event.EventMessage.UPDATE, null);
    }

}
