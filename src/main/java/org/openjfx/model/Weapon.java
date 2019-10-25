package org.openjfx.model;

class Weapon extends Item {

    private final int atk;

    public Weapon(String s, int atk){
        super(s);
        this.atk = atk;
    }

    public float getAtk(){
        return this.atk;
    }


}
