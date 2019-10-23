package org.openjfx.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    Model model = new Model();

    @Test
    public void modelMovePlayerTest(){
        model.movePlayerUp();
        model.movePlayerDown();
        model.movePlayerLeft();
        model.movePlayerRight();

        assertEquals(0,model.world.player.getXcoord(),0);
        assertEquals(0,model.world.player.getYcoord(),0);
    }

}