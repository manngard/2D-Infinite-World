package org.openjfx.model.tilepackage;

import org.openjfx.model.Entity;

public class Tile extends Entity {
    //Describes if the Tile can be moved through
    private boolean isSolid;

    public Tile(String id){
        super(id);
    }
}
