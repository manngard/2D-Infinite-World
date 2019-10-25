package org.openjfx.model.entity;

import org.openjfx.model.item.Item;
import org.openjfx.model.item.ItemFactory;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Chest extends Entity{

    private final Item[] inventory = new Item[4];
    public Chest(String a,double b,double c){
        super(a,b,c);
        for(int r = 0;r < 4; r++){
            inventory[r] = ItemFactory.getInstance().getRandomItem();
        }
    }

    public Item getItem(int index) {
        return inventory[index];
    }

    public int getInventorySize() {
        return inventory.length;
    }


}
