package org.openjfx.model;

public abstract class Entity{

    //Describes what type of Entity
    protected final String id;
    Coordinates coords;

    protected Entity(String i, double a, double b){
        this.id = i;
        coords = new Coordinates(a,b);
    }

    public String getId() {
        return id;
    }

    public double getXcoord() {
        return coords.getxCoord();
    }

    public double getYcoord() {
        return coords.getyCoord();
    }

    public Coordinates getCoords() {
        return coords;
    }
}
