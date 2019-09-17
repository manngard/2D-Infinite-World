package org.openjfx.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player("1", "Eddy", 0, 0, true, 0, 0 );

    @Test
    public void playerTest(){
        Assert.assertEquals(player.isAlive(), true);
    }
}