package org.openjfx.model;

public abstract class Entity {

    protected String id;
    protected int xcoord, ycoord;

    public Entity(String i, int a, int b){
        this.id = i;
        this.xcoord = a;
        this.ycoord = b;
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
