package org.openjfx.model;

import org.junit.Assert;
import org.junit.Test;
import org.openjfx.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WorldTest {


    private final World world = new World();

    // World size test
    @Test
    public void worldConstructorTest(){

        int columns = 0;
        for (List<Tile> worldrow: world.worldGrid){
            assertEquals(worldrow.size(), world.worldVerticalSideLength, 0.0);
            columns++;
        }
        assertEquals(columns, world.worldHorizontalSideLength, 0.0);
    }

    //  Test to see if the attackHit method in world class works. This also ensures that the method inSight works.
    @Test
    public void worldCombatTest(){
        List<Combatant> enemies = new ArrayList<>();
        Enemy enemy = new Enemy("e1", -1, 0, 100, 20, 2, 0);
        enemies.add(enemy);
        Assert.assertTrue(world.inSight(world.player, enemy));
        world.attackHit(world.player, world.combatantAttacks(world.player, enemies));
        Assert.assertEquals(80, enemy.getHp());

        Enemy enemy2 = new Enemy("e2", 100, 100, 100, 20, 2, 0);
        enemies.add(enemy2);

        Assert.assertFalse(world.inSight(world.player, enemy2));

        world.combatantAttacks(world.player, enemies);
        world.attackHit(world.player, world.combatantAttacks(world.player, enemies));
        Assert.assertEquals(60, enemy.getHp());
        Assert.assertEquals(100, enemy2.getHp());
    }


}