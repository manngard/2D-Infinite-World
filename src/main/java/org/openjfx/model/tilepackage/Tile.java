package org.openjfx.model.tilepackage;

import org.openjfx.model.Chest;
import org.openjfx.model.Entity;

public class Tile extends Entity {
    //Describes if the Tile can be moved through
    private boolean isSolid;
    public Chest chest;

    public Tile(Chest chest, String id, double x, double y){
        super(id,x,y);
        this.chest = chest;
    }
    public Chest getChest() {
        return chest;
    }
}
