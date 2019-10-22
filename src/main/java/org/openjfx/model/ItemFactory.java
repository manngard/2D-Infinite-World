package org.openjfx.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private final List<Pair<String, Integer>> weapontypes = new ArrayList<>();
    private final List<Pair<String, Integer>> armortypes = new ArrayList<>();
    private static ItemFactory instance;
    private Random rand;

    private ItemFactory(){
        weapontypes.add(new Pair<>("Sword", 20));
        weapontypes.add(new Pair<>("Axe", 30));

        armortypes.add(new Pair<>("Armor", 10));
    }

    public Item getRandomItem(){
        rand = new Random();
        int randIndex;
        String id;
        int bonusStat;
        int itemtype = rand.nextInt(2);
        switch(itemtype){
            case 0:
                randIndex = rand.nextInt(weapontypes.size());
                id = weapontypes.get(randIndex).getKey();
                bonusStat = weapontypes.get(randIndex).getValue();
                return new Weapon(id, bonusStat);
            case 1:
                randIndex = rand.nextInt(armortypes.size());
                id = armortypes.get(randIndex).getKey();
                bonusStat = armortypes.get(randIndex).getValue();
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
