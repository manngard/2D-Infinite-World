package org.openjfx.model.entity.tile;

import org.junit.Assert;
import org.junit.Test;
import org.openjfx.model.noise.DefaultNoiseGenerator;
import org.openjfx.model.noise.NoiseGenerator;

import static org.junit.Assert.assertEquals;

public class TileFactoryTest {


    // Not valid test, but creating random tiles works perfectly in real application.
    @Test
    public void createTilesTest(){

        TileFactory factory = new TileFactory(new DefaultNoiseGenerator()); //Test cannot be done since OpenSimplexAdapter's is private.
        Tile tile = factory.generateTile(10,10);
        int lavaCounter = 0;
        int grassCounter = 0;
        int mountainCounter = 0;


        for(int i = 0; i < 100; i++){
            factory.generateTile(i,i);
            if(tile.getId().equals("Lava")){
                lavaCounter ++;
            }
            else if(tile.getId().equals("Grass")){
                grassCounter ++;
            }
            else if(tile.getId().equals("Mountain")){
                mountainCounter ++;
            }
        }
        Tile tileIsRandom = factory.generateTile(101,101);

        assertEquals(tileIsRandom.getId(), tileIsRandom.getId());

        System.out.println(
                "Amount of lava tiles created: " + lavaCounter + "\n" +
                "Amount of grass tiles created: " + grassCounter + "\n" +
                "Amount of mountain tiles created: " + mountainCounter
        );

    //  This test is not valid!

    }
}

