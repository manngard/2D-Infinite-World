package org.openjfx.model;

public abstract class Entity{

    //Describes what type of Entity
    protected String id;
    protected int xcoord, ycoord;

    public Entity(String i, int a, int b){
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

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

}
