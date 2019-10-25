package org.openjfx.model.item;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private final List<Pair<String, Integer>> weaponTypes = new ArrayList<>();
    private final List<Pair<String, Integer>> armorTypes = new ArrayList<>();
    private static ItemFactory instance;

    private ItemFactory(){
        weaponTypes.add(new Pair<>("Sword", 20));
        weaponTypes.add(new Pair<>("Axe", 30));

        armorTypes.add(new Pair<>("Armor", 10));
    }

    /*Generates a random item from available ones in weapontypes and armortypes*/

    public Item getRandomItem(){
        Random rand = new Random();
        int randIndex;
        String id;
        int bonusStat;
        int itemType = rand.nextInt(2);
        switch(itemType){
            case 0:
                randIndex = rand.nextInt(weaponTypes.size());
                id = weaponTypes.get(randIndex).getKey();
                bonusStat = weaponTypes.get(randIndex).getValue();
                return new Weapon(id, bonusStat);
            case 1:
                randIndex = rand.nextInt(armorTypes.size());
                id = armorTypes.get(randIndex).getKey();
                bonusStat = armorTypes.get(randIndex).getValue();
                return new Armor(id, bonusStat);
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
