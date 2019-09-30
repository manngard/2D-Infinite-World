package org.openjfx.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player("1", 0, 0, 100, 50, 32 );

    @Test
    public void createPlayerTest(){
        Assert.assertEquals(player.getHp(), 100);
        Assert.assertEquals(player.getExp(), 0);
        Assert.assertEquals(player.getId(), "1");
        //Assert.assertEquals(player.getYcoord(), 0);
        //Assert.assertEquals(player.getXcoord(), 0);
        //Assert.assertEquals(player.getAtkRange(), 100);
        Assert.assertEquals(player.getAtk(), 50);
    }

    @Test   //Test of Hp methods
    public void hpTest(){
        // Test of decHp when decAmount < Hp
        player.decHp(50);
        Assert.assertEquals(player.getHp(), 50);

        player.incHp(30);
        Assert.assertEquals(player.getHp(), 80);

        player.incHp(500);
        Assert.assertEquals(player.getHp(), 580);

        player.decHp(200);
        Assert.assertEquals(player.getHp(), 380);
    }

    @Test   //Test of Exp methods
    public void expTest(){
        player.incExp(200);
        player.decExp(50);

        Assert.assertEquals(player.getExp(), 150);
    }

    @Test   //  Test of move functions
    public void moveTest(){
        /*player.moveUp();
        player.moveDown();
        player.moveLeft();
        player.moveRight();*/

        //Assert.assertEquals(player.getXcoord(), 0);
        //Assert.assertEquals(player.getYcoord(), 0);
    }
}