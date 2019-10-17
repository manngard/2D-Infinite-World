package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {
    private List<String> enemyTypes = new ArrayList<>();
    int spawnAreaSide = 10000;

    public EntityFactory(){
        enemyTypes.add("Goblin");
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
        double randX = rand.nextInt(400) - 200;
        double randY = rand.nextInt(400) - 200;
        return new Chest("Chest",randX,randY);
    }
}