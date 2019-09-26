package org.openjfx.model;

import org.openjfx.model.tilepackage.Tile;
import org.openjfx.model.tilepackage.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final TileFactory tileFactory = new TileFactory();
    List<List<Tile>> worldGrid;
    int worldVerticalSideLength;
    int worldHorizontalSideLength;
    public Player player;

    //Initiates 10 rows for the grid matrix, then fills every row with 10 Tile objects
    public World(){
        player = new Player("Player",200,100,10,10, 1);
        this.worldHorizontalSideLength = 20;
        this.worldVerticalSideLength = 13;

        worldGrid = new ArrayList<List<Tile>>();
        for (int i = 0; i<worldHorizontalSideLength;i++){
            worldGrid.add(new ArrayList<Tile>());
        }
        for (List<Tile> worldrow : worldGrid){
            for (int i = 0; i<worldVerticalSideLength;i++){
                worldrow.add(tileFactory.getTile("Grass"));
            }
        }
    }
    public List<List<Tile>> getWorldGrid() {
        return worldGrid;
    }
    public void makeAttack(Entity e){


    }
}
