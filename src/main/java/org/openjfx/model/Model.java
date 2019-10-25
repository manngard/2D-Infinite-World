package org.openjfx.model;

import org.openjfx.model.entity.Chest;
import org.openjfx.model.entity.Combatant;
import org.openjfx.model.entity.Movable;
import org.openjfx.model.event.EventListener;
import org.openjfx.model.item.Item;
import org.openjfx.model.noise.NoiseGenerator;

import org.openjfx.model.event.Event;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Model {

    final World world;

    private final Event<ModelEventMessage> hasUpdateEvent;

    public Model() {
        this(null);
    }

    public Model(NoiseGenerator noiseGenerator) {
        world = new World(noiseGenerator);
        hasUpdateEvent = new Event<>();
    }

    public void movePlayerUp() {
        world.player.setDirection(Movable.Direction.UP);
        if(world.isPathFree(world.player, world.getActiveEnemies())) {
            world.player.move(Movable.Direction.UP);
            world.updateWorldGrid();
        }
    }

    public void movePlayerDown() {
        world.player.setDirection(Movable.Direction.DOWN);
        if(world.isPathFree(world.player, world.getActiveEnemies())) {
            world.player.move(Movable.Direction.DOWN);
            world.updateWorldGrid();
        }
    }

    public void movePlayerRight() {
        world.player.setDirection(Movable.Direction.RIGHT);
        if(world.isPathFree(world.player, world.getActiveEnemies())) {
            world.player.move(Movable.Direction.RIGHT);
            world.updateWorldGrid();
        }
    }

    public void movePlayerLeft() {
        world.player.setDirection(Movable.Direction.LEFT);
        if(world.isPathFree(world.player, world.getActiveEnemies())) {
            world.player.move(Movable.Direction.LEFT);
            world.updateWorldGrid();
        }
    }

    private void modelHasUpdate() {
        hasUpdateEvent.dispatch(ModelEventMessage.UPDATE, world);
    }

    public void initModel() {
        modelHasUpdate();
    }

    public void playerAttacks() {
        world.attackHit(world.player, world.combatantAttacks(world.player, world.getActiveEnemies()));
    }


    public void playerInteracts() {
        for (Chest chest : world.getActiveChests()) {
            if (world.isEntityWithinDistance(chest,world.getPlayer(), 1.2) & world.inSight(world.getPlayer(), chest)) {
                for (int i = 0; i < chest.getInventorySize(); i++)
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

    public void registerListener(EventListener<ModelEventMessage> listener) {
        hasUpdateEvent.addListener(listener);
    }

    public void runTick() {
        this.moveMobsInWorld();
        this.mobsAttack();
        this.modelHasUpdate();
    }

}

