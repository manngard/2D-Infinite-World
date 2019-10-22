package org.openjfx.model;

public class Weapon extends Item {

    private int atk;

    public Weapon(String s, int atk){
        super(s);
        this.atk = atk;
    }

    public float getAtk(){
        return this.atk;
    }


}
