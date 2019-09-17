package org.openjfx.model;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class WorldTest {
    private World world;

    @Before
    public void createWorld(){
        this.world = new World();
    }
    @Test
    public void worldConstructorTest(){
        int columns = 0;
        for (List<Tile> worldrow: world.worldGrid){
            assertTrue(worldrow.size() == world.worldSideLength);
            columns++;
        }
        assertTrue(columns == world.worldSideLength);
    }

}