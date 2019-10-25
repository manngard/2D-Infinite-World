package org.openjfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class EnemyFactory {
    private final List<String> enemyTypes = new ArrayList<>();

    public EnemyFactory(){
        enemyTypes.add("Goblin");
        enemyTypes.add("Rat");
    }
    public Combatant generateEnemy(int spawnAreaSide) {
        Random rand = new Random();
        Coordinates coordinates = Coordinates.generateRandomCoordinates(spawnAreaSide);
        int randIndex = rand.nextInt(enemyTypes.size());
        return new Enemy(enemyTypes.get(randIndex),coordinates.getxCoord(),coordinates.getyCoord(),100,30,2, 0);
    }
}
