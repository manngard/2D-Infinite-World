package org.openjfx;

public class Armor extends Item{

    private float def;

    public Armor(String s){
        super(s);
    }

    public float getDef(){
        return this.def;
    }
    public void  setDef(float f){
        this.def = f;
    }

}
