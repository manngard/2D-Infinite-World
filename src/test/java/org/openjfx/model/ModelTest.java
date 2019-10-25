package org.openjfx.model;

import org.junit.Before;
import org.junit.Test;
import org.openjfx.model.entity.Chest;
import org.openjfx.model.entity.Enemy;
import org.openjfx.model.entity.Movable;

import static org.junit.Assert.*;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class ModelTest {
    private Model model;

    @Before
    public void beforeEach(){
        model = new Model();
    }

    @Test
    public void modelMovePlayerTest() {
        final double x = model.world.player.getXCoord();
        final double y = model.world.player.getYCoord();
        model.movePlayerUp();
        model.movePlayerDown();
        model.movePlayerLeft();
        model.movePlayerRight();

        assertEquals(x,model.world.player.getXCoord(),0.01);
        assertEquals(y,model.world.player.getYCoord(),0.01);
    }

    @Test
    public void interactWithChest(){
        Chest chest = new Chest("Chest", -1, 0);
        model.world.getActiveChests().add(chest);


        model.playerInteracts();

        assertEquals(model.world.player.getDirection(), Movable.Direction.LEFT);
        assertTrue(!model.world.player.getInventory().equals(null));    // Player has interacted with the chest and the player's inventory has been filled with the items from the chest


        model.selectInventory(4);   // Item at players inventory slot 4 has been selected.

        assertTrue(model.world.player.getInventory()[3].getIsItemSelected());

    }

    @Test
    public void playerAttacksEnemy(){
        Enemy enemy = new Enemy("e",-1,0,100,30,2,0);
        model.world.getActiveEnemies().add(enemy);
        model.playerAttacks();
        assertEquals(enemy.getHp(),80); // Player has attacked enemy in front of him and damaged his hp from 100 down to 80.

        Enemy enemy2 = new Enemy("e",model.world.player.getXCoord()+1,model.world.player.getYCoord(),100,30,2,0);
        model.world.getActiveEnemies().add(enemy2);
        model.mobsAttack();
        model.mobsAttack();
        assertEquals(model.world.player.getHp(), 70);
    }

    @Test
    public void moveMobsInWorld(){
        Enemy enemy = new Enemy("e",-1,0,100,30,2,0);
        double prevXCoord = enemy.getXCoord();
        double prevYCoord = enemy.getYCoord();
        model.world.getActiveEnemies().add(enemy);

        model.moveMobsInWorld();
        assertTrue(enemy.getXCoord() != prevXCoord);
        assertTrue(enemy.getYCoord() != prevXCoord);
    }
}