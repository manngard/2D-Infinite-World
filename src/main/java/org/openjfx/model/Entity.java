package org.openjfx.model;

public abstract class Entity{

    //Describes what type of Entity
    protected String id;
    Coords coords;
    //protected double xcoord, ycoord;

    public Entity(String i, double a, double b){
        this.id = i;
        /*this.xcoord = a;
        this.ycoord = b;*/
        coords = new Coords(a,b);
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

    public Coords getCoords() {
        return coords;
    }
}
