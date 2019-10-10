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

        world.attackHit(world.player, world.playerAttacks(world.player, world.getEnemies()));

    }

    public void moveMob(Enemy enemy){

        //Int to use for future random mob movement
        //int rand = (int)Math.ceil(Math.random() * 2);

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