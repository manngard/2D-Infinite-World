package org.openjfx.model.tilepackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileFactory {
    List<String> tiletypes = new ArrayList<>();

    public TileFactory(){
        //more tiletypes to be added below
        tiletypes.add("Lava");
        tiletypes.add("Grass");
        tiletypes.add("Mountain");
    }


    public Tile getTile(String id, double x, double y){
        for (String tiletype: tiletypes){
            if (tiletype.equals(id)){
                return new Tile(id,x,y);
            }
        }
        //Should never occur
        return null;
    }
    public Tile getRandomTile(double x, double y){
        Random rand = new Random();
        int randPos = rand.nextInt(tiletypes.size());
        String id = tiletypes.get(randPos);
        return new Tile(id,x,y);
    }
}
