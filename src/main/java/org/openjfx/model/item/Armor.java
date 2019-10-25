package org.openjfx.model.item;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Armor extends Item{

    private final int def;

    public Armor(String s, int def){
        super(s);
        this.def = def;
    }

    public float getDef(){
        return this.def;
    }

}
