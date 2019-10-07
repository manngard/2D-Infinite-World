package org.openjfx.model;

public abstract class Item {

    private String id;


    public Item(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
}
