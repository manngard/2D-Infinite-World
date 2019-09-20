package org.openjfx.model;

public class Tile {

    //Describes what type of Tile
    String id;

    //Describes if the Tile can be moved through
    boolean isSolid;

    public Tile(String id){
        this.id = id;
    }

}
