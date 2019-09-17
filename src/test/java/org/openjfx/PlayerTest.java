package org.openjfx;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {


    Player player = new Player("1", "Eddy", 0, 0, true, 100, 0);
    //World world1 = new World();


    @Test
    public void playerIsAliveTest(){
        Assert.assertEquals(player.isAlive, true);
    }

}