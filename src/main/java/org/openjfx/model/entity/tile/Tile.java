package org.openjfx.model.entity.tile;

import org.openjfx.model.entity.Entity;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Encompasses the Tile object behaviour.
  Used by: World and TileFactory
  Uses: Entity
  */

public class Tile extends Entity {
    //Describes if the Tile can be moved through
    private boolean isSolid;

    public Tile(String id, double x, double y){
        super(id,x,y);
        if(this.id == "Mountain" || this.id == "Lava"){
            this.isSolid = true;
        }
    }

    public boolean getISSolid(){
        return this.isSolid;
    }
}
