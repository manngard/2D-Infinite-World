package org.openjfx.model.tilepackage;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileFactoryTest {


    @Test
    public void createTilesTest(){
        TileFactory factory = new TileFactory();
        Tile tile = factory.getTile("Lava");

        Assert.assertEquals("Lava", tile.getId());

    }
}

