package org.openjfx.model;

public class Chest extends Entity{

    public Item[] inventory = new Item[4];
    public Chest(String a,int b,int c,Item[] i){
        super(a,b,c);
        for(int r = 0;r < 4; r++){

            inventory[r] = i[r];
        }
    }
}
