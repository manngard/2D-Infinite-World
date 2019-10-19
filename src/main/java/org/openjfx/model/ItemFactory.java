package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private final List<String> weapontypes = new ArrayList<>();
    private final List<String> armortypes = new ArrayList<>();
    private static ItemFactory instance;


    private ItemFactory(){
        weapontypes.add("Sword");
        weapontypes.add("Axe");

        armortypes.add("Armor");
    }

    public Item getRandomItem(){
        Random rand = new Random();
        int randIndex;
        String id;
        int itemtype = rand.nextInt(2);
        switch(itemtype){
            case 0:
                randIndex = rand.nextInt(weapontypes.size());
                id = weapontypes.get(randIndex);
                return new Weapon(id);
            case 1:
                randIndex = rand.nextInt(armortypes.size());
                id = armortypes.get(randIndex);
                return new Armor(id);
        }
        return null; //will never happen
    }

    public static ItemFactory getInstance() {
        if(instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }
}
