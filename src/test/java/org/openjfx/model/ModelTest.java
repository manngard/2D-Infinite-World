package org.openjfx.model;

import org.junit.Test;
import org.openjfx.model.entity.Chest;
import org.openjfx.model.entity.ChestFactory;
import org.openjfx.model.entity.Enemy;
import org.openjfx.model.entity.Movable;

import static org.junit.Assert.*;

public class ModelTest {

    private final Model model = new Model();
    private final ChestFactory chestFactory = new ChestFactory();

    @Test
    public void modelMovePlayerTest() {
        model.movePlayerUp();
        model.movePlayerDown();
        model.movePlayerLeft();
        model.movePlayerRight();

        assertEquals(0,model.world.player.getXcoord(),0);
        assertEquals(0,model.world.player.getYcoord(),0);
    }

    @Test
    public void interactWithChest(){
        Chest chest = new Chest("Chest", -1, 0);
        model.world.getActiveChests().add(chest);


        model.playerInteracts();

        assertEquals(model.world.player.getDirection(), Movable.Direction.LEFT);
        assertTrue(!model.world.player.getInventory().equals(null));    // Player has interacted with the chest and the player's inventory has been filled with the items from the chest

    }

    @Test
    public void playerAttacksEnemy(){
        Enemy enemy = new Enemy("e",-1,0,100,30,2,0);
        model.world.getActiveEnemies().add(enemy);

        model.playerAttacks();
        assertEquals(enemy.getHp(),80); // Player has attacked enemy in front of him and damaged his hp from 100 down to 80.
    }

}