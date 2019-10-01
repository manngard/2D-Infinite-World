package org.openjfx.model;

public class Chest extends Entity{

    public Item[] inventory = new Item[4];
    public Chest(String a,double b,double c){
        super(a,b,c);
        for(int r = 0;r < 4; r++){
            inventory[r] = ItemFactory.getInstance().getRandomItem();
        }
    }
}
