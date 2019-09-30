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
        double playerxcoord = world.player.xcoord;
        double playerycoord = world.player.ycoord;
        double distance = Math.sqrt(Math.pow((playerxcoord - enemy.xcoord),2) + Math.pow((playerycoord - enemy.ycoord),2));
        if(distance <= maxDistance)
            return true;
        return false;
    }

    public void moveMobToPlayer(Enemy enemy){
        double xDistance = Math.abs(world.player.xcoord - enemy.xcoord);
        double yDistance = Math.abs(world.player.ycoord - enemy.ycoord);

        if(xDistance >= yDistance && enemy.xcoord > world.player.xcoord){
            enemy.move(Movable.Direction.LEFT);
        }
        else if(xDistance >= yDistance && enemy.xcoord < world.player.xcoord){
            enemy.move(Movable.Direction.RIGHT);
        }
        else if(yDistance > xDistance && enemy.ycoord > world.player.ycoord){
            enemy.move(Movable.Direction.DOWN);
        }
        else if(yDistance > xDistance && enemy.ycoord < world.player.ycoord){
            enemy.move(Movable.Direction.UP);
        }
    }

}
