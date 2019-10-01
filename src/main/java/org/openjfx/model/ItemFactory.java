package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    List<String> itemtypes = new ArrayList<>();
    private static ItemFactory instance;


    private ItemFactory(){
        itemtypes.add("Sword");
        itemtypes.add("Axe");
    }

    public Item getRandomItem(){
        Random rand = new Random();
        int randPos = rand.nextInt(itemtypes.size());
        String id = itemtypes.get(randPos);
        return new Weapon(id); //only creates weapons for now
    }

    public static ItemFactory getInstance() {
        if(instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }
}
