package org.openjfx.model;

public abstract class Item {

    private String id;


    public Item(String id){
        this.id = id;
    }

    public String getName(){
        return this.id;
    }
    public void setName(String id){
        this.id = id;
    }
}
