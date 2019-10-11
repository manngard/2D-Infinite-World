package org.openjfx.model;

import org.openjfx.model.noise.NoiseGenerator;

import org.openjfx.utils.event.Event;

public class Model {


    private World world;

    public Event<EventMessage> hasUpdateEvent;

    public Model() {
        this(null);
    }

    public Model(NoiseGenerator noiseGenerator) {
        world = new World(noiseGenerator);
        hasUpdateEvent = new Event<>();
    }

    public void movePlayerUp() {
        System.out.println("Up");
        world.player.move(Movable.Direction.UP);
        world.updateWorldGrid();
    }

    public void movePlayerDown() {
        System.out.println("Down");
        world.player.move(Movable.Direction.DOWN);
        world.updateWorldGrid();
    }

    public void movePlayerRight() {
        System.out.println("Right");
        world.player.move(Movable.Direction.RIGHT);
        world.updateWorldGrid();
    }

    public void movePlayerLeft() {
        System.out.println("Left");
        world.player.move(Movable.Direction.LEFT);
        world.updateWorldGrid();
    }

    public void modelHasBeenUpdated() {
        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }


    public void playerAttacks() {
        world.attackHit(world.player, world.playerAttacks(world.player, world.getEnemies()));
        modelHasBeenUpdated();

    }
    public void playerInteracts(){
        for (Chest chest : world.getChests()){
            if (world.isEntityWithinDistance(chest,1) & world.inSight(world.getPlayer(),chest)){
                for (int i = 0; i<4; i++)
                    world.player.setItem(chest.getItem(i),i);
            }
        }

        selectInventory(1);
        modelHasBeenUpdated();
    }

    public void selectInventory(int inventoryNumber) {

        for (Item item : world.player.getInventory()) {
            if (item.getIsItemSelected()) {
                item.setToNotSelected();
                break;
            }
        }
        world.player.getInventory()[inventoryNumber - 1].setToSelected();


    }


    public void moveMobsInWorld(){
        world.moveMobs();
    }

}

