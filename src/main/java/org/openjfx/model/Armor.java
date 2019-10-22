package org.openjfx.model;

public class Armor extends Item{

    private int def;

    public Armor(String s, int def){
        super(s);
        this.def = def;
    }

    public float getDef(){
        return this.def;
    }

}
