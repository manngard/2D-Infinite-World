package org.openjfx.model;

import org.junit.Assert;
import org.junit.Test;
import org.openjfx.model.entity.Combatant;
import org.openjfx.model.entity.Enemy;
import org.openjfx.model.entity.Movable;
import org.openjfx.model.entity.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class WorldTest {


    private final World world = new World();

    //  Test to see if the attackHit method in world class works. This also ensures that the method inSight works.
    @Test
    public void worldCombatTest() {
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

    @Test
    public void CollisionTest() {
        List<Combatant> enemies = new ArrayList<>();
        Enemy enemy = new Enemy("e1", -1.0, 0, 100, 20, 2, 0);
        enemies.add(enemy);
        world.player.setDirection(Movable.Direction.LEFT);
        Assert.assertFalse(world.isPathFree(world.player, enemies));
        world.player.setDirection(Movable.Direction.RIGHT);
        Assert.assertTrue(world.isPathFree(world.player, enemies));
    }

    @Test
    public void moveMobsTest() {

        world.getActiveEnemies().clear();
        world.getActiveEnemies().add(new Enemy("Tester", 2, 2, 3, 3, 3, 3));
        world.player.move(Movable.Direction.RIGHT);
        Assert.assertTrue(world.getActiveEnemies().get(0).getId().equals("Tester"));
        world.getActiveEnemies().clear();
        world.getActiveEnemies().add(new Enemy("Tester", 30, 30, 3, 3, 3, 3));
        world.moveMobs();
        for (Combatant c : world.getActiveEnemies()) {

            Assert.assertFalse(c.getId().equals("Tester"));

        }


    }
}