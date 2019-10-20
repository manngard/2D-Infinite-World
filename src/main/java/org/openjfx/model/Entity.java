package org.openjfx.model;

public abstract class Entity{

    //Describes what type of Entity
    protected String id;
    protected Coordinates coords;

    public Entity(String i, double a, double b){
        this.id = i;
        coords = new Coordinates(a,b);
    }
    public Entity(String i){
        this.id = i;
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
