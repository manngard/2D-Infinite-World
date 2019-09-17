package org.openjfx.model;

public class Tile {

    //Describes what type of Tile
    Enum<Tiletype> tiletype;

    //Describes if the Tile can be moved through
    boolean isSolid;

    public Tile(Tiletype tiletype){
        this.tiletype = tiletype;
    }

    public Enum<Tiletype> getTiletype() {
        return tiletype;
    }
}
