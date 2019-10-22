package org.openjfx.model;

import org.openjfx.model.noise.NoiseGenerator;

import org.openjfx.utils.event.Event;

public class Model {

    World world;

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
        world.player.setDirection(Movable.Direction.UP);
        if(world.isPathFree(world.player)) {
            world.player.move(Movable.Direction.UP);
            world.updateWorldGrid();
        }
    }

    public void movePlayerDown() {
        System.out.println("Down");
        world.player.setDirection(Movable.Direction.DOWN);
        if(world.isPathFree(world.player)) {
            world.player.move(Movable.Direction.DOWN);
            world.updateWorldGrid();
        }
    }

    public void movePlayerRight() {
        System.out.println("Right");
        world.player.setDirection(Movable.Direction.RIGHT);
        if(world.isPathFree(world.player)) {
            world.player.move(Movable.Direction.RIGHT);
            world.updateWorldGrid();
        }
    }

    public void movePlayerLeft() {
        System.out.println("Left");
        world.player.setDirection(Movable.Direction.LEFT);
        if(world.isPathFree(world.player)) {
            world.player.move(Movable.Direction.LEFT);
            world.updateWorldGrid();
        }
    }

    public void modelHasBeenUpdated() {
        hasUpdateEvent.dispatch(EventMessage.UPDATE, world);
    }

    public void playerAttacks() {
        world.attackHit(world.player, world.combatantAttacks(world.player, world.getActiveEnemies()));
    }


    public void playerInteracts() {
        for (Chest chest : world.getActiveChests()) {
            if (world.isEntityWithinDistance(chest,world.getPlayer(), 1) & world.inSight(world.getPlayer(), chest)) {
                for (int i = 0; i < 4; i++)
                    world.player.setItem(chest.getItem(i), i);
                if(world.player.getSelectedItem() == 0){
                    selectInventory(1);
                }
                else
                    selectInventory(world.player.getSelectedItem());
            }
        }
    }

    public void selectInventory(int inventoryNumber) {
        if (world.player.getInventory()[inventoryNumber - 1] != null) {
            for (Item item : world.player.getInventory()) {
                if (item.getIsItemSelected()) {
                    item.setToNotSelected();
                    break;
                }
            }
            world.player.getInventory()[inventoryNumber - 1].setToSelected();
            world.player.setSelectedItem(inventoryNumber);
        }
    }

    public void moveMobsInWorld() {
        world.moveMobs();
    }


    public void mobsAttack() {
        for (Combatant enemy : world.getActiveEnemies()) {
            world.attackHit(enemy, world.combatantAttacks(enemy, world.getPlayers()));
        }
    }

}

