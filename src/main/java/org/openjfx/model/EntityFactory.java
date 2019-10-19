package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {
    private final List<String> enemyTypes = new ArrayList<>();
    private int spawnAreaSide = 5000;

    public EntityFactory(){
        enemyTypes.add("Goblin");
        enemyTypes.add("Rat");
    }

    public Combatant generateEnemy() {
        Random rand = new Random();
        double randX = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        double randY = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        int randIndex = rand.nextInt(enemyTypes.size());
        return new Enemy(enemyTypes.get(randIndex),randX,randY,10,1,2);
    }
    public Chest generateChest() {

        Random rand = new Random();
        double randX = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        double randY = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        return new Chest("Chest",randX,randY);
    }
}