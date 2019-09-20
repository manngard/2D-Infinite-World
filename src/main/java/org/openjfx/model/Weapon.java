package org.openjfx.model;

public class Weapon extends Item {

    private float atk;

    public Weapon(String s){
        super(s);
    }

    public float getAtk(){
        return this.atk;
    }
    public void  setAtk(float f){
        this.atk = f;
    }

}
