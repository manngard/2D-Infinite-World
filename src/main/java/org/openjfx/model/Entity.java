package org.openjfx.model;

public abstract class Entity{

    //Describes what type of Entity
    protected String id;
    protected double xcoord, ycoord;

    public Entity(String i, double a, double b){
        this.id = i;
        this.xcoord = a;
        this.ycoord = b;
    }
    public Entity(String i){
        this.id = i;
    }

    public String getId() {
        return id;
    }

    public double getXcoord() {
        return xcoord;
    }

    public double getYcoord() {
        return ycoord;
    }

}
