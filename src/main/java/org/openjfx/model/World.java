package org.openjfx.model;

import org.openjfx.model.tilepackage.Tile;
import org.openjfx.model.tilepackage.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final TileFactory tileFactory = new TileFactory();
    List<List<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;

    public Player player;

    //Initiates 10 rows for the grid matrix, then fills every row with 10 Tile objects
    public World(){
        player = new Player("Player",0,0,true,10,10);
        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;
        double xCoord = 0 - ((worldHorizontalSideLength - 1)/2) - 1;
        double yCoord;

        worldGrid = new ArrayList<List<Tile>>();
        for (int i = 0; i<worldHorizontalSideLength;i++){
            worldGrid.add(new ArrayList<Tile>());
        }
        for (List<Tile> worldrow : worldGrid){
            xCoord++;
            yCoord = (0 - (worldVerticalSideLength - 1)/2);
            for (int i = 0; i<worldVerticalSideLength;i++){
                worldrow.add(tileFactory.getRandomTile(xCoord,yCoord));
                yCoord++;
            }
        }
    }
    public List<List<Tile>> getWorldGrid() {
        return worldGrid;
    }
}
