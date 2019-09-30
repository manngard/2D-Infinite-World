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
        world.player.moveUp();
        modelHasBeenUpdated();
    }

    public void movePlayerDown() {
        //TODO
        System.out.println("Down");
        world.player.moveDown();
        modelHasBeenUpdated();
    }

    public void movePlayerRight() {
        //TODO
        System.out.println("Right");
        world.player.moveRight();
        modelHasBeenUpdated();
    }

    public void movePlayerLeft() {
        //TODO
        System.out.println("Left");
        world.player.moveLeft();
        modelHasBeenUpdated();

    }

    public void modelHasBeenUpdated() {
        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }

    public boolean isMobWithinDistance(Entity entity){
        int playerxcoord = world.player.xcoord;
        int playerycoord = world.player.ycoord;
        double distance = Math.sqrt(Math.pow((playerxcoord - entity.xcoord),2) + Math.pow((playerycoord - entity.ycoord),2));
        if(distance <= maxDistance)
            return true;
        return false;
    }

    public void moveMobToPlayer(Entity entity){
        int xDistance = world.player.xcoord - entity.xcoord;
        int yDistance = world.player.ycoord - entity.ycoord;

        if(xDistance > yDistance){
            
        }
    }

}
