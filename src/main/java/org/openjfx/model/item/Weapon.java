package org.openjfx.model.item;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Weapon extends Item {

    private final int atk;

    public Weapon(String s, int atk){
        super(s);
        this.atk = atk;
    }

    public float getAtk(){
        return this.atk;
    }


}
