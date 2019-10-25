package org.openjfx.model.entity;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Encompasses the behaviour shared for all Entities.
  Used by: Combatant and World
  Uses:
  */

public abstract class Entity{

    //Describes what type of Entity
    protected final String id;
    final Coordinates coords;

    protected Entity(String i, double a, double b){
        this.id = i;
        coords = new Coordinates(a,b);
    }

    public String getId() {
        return id;
    }

    public double getXCoord() {
        return coords.getXCoord();
    }

    public double getYCoord() {
        return coords.getYCoord();
    }

    public Coordinates getCoords() {
        return coords;
    }
}
