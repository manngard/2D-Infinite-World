package org.openjfx.model.item;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Encompassing the Item Object behavior
  Used by: Armor, Weapon, Chest, Player, ItemFactory
  */

public abstract class Item {

    private String id;
    private  boolean isSelected = false;


    Item(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setToSelected(){
        isSelected = true;
    }

    public void setToNotSelected(){
        isSelected = false;
    }

    public boolean getIsItemSelected(){
        return isSelected;
    }

}
