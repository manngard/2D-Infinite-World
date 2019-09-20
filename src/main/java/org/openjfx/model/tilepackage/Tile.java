package org.openjfx.model.tilepackage;

public class Tile {

    //Describes what type of Tile
    private String id;

    //Describes if the Tile can be moved through
    private boolean isSolid;

    Tile(String id){
        this.id = id;
    }

}
