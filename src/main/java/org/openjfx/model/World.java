package org.openjfx.model;

import org.openjfx.model.tilepackage.Tile;
import org.openjfx.model.tilepackage.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final TileFactory tileFactory = new TileFactory();
    List<List<Tile>> worldGrid;
    int worldSideLength;
    Player player;

    //Initiates 10 rows for the grid matrix, then fills every row with 10 Tile objects
    public World(){
        this.worldSideLength = 10;

        worldGrid = new ArrayList<List<Tile>>();
        for (int i = 0; i<worldSideLength;i++){
            worldGrid.add(new ArrayList<Tile>());
        }
        for (List<Tile> worldrow : worldGrid){
            for (int i = 0; i<worldSideLength;i++){
                worldrow.add(tileFactory.getTile("Grass"));
            }
        }
    }

}
