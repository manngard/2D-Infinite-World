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

        if(distance(world.player, enemy) <= maxDistance)
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

    public double distance(Entity a, Entity b){

        double xDist = Math.abs(a.xcoord - b.xcoord);
        double yDist = Math.abs(a.ycoord - b.ycoord);
        return Math.sqrt((yDist * yDist) + (xDist * xDist));
    }

    public void playerAttacks(){


    }

}
