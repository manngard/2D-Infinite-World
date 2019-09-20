package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public Tile getTile(String id){
        List<String> tiletypes = new ArrayList<>();
        //more tiletypes to be added below
        tiletypes.add("Lava");
        tiletypes.add("Grass");
        tiletypes.add("Mountain");

        for (String tiletype: tiletypes){
            if (tiletype.equals(id)){
                return new Tile(id);
            }
        }
        //Should never occur
        return null;
    }
}
