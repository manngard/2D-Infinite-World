package org.openjfx.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {
    private final List<String> enemyTypes = new ArrayList<>();
    private int spawnAreaSide = 5000;
    private Random rand;

    public EntityFactory(){
        enemyTypes.add("Goblin");
        enemyTypes.add("Rat");
    }

    public Combatant generateEnemy() {
        Coordinates coordinates = generateRandomCoordinates();
        int randIndex = rand.nextInt(enemyTypes.size());
        return new Enemy(enemyTypes.get(randIndex),coordinates.getxCoord(),coordinates.getyCoord(),100,30,2, 0);
    }
    public Chest generateChest() {

        Coordinates coordinates = generateRandomCoordinates();
        return new Chest("Chest",coordinates.getxCoord(),coordinates.getyCoord());
    }

    public Coordinates generateRandomCoordinates(){
        rand = new Random();
        double randX = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        double randY = rand.nextInt(spawnAreaSide) - spawnAreaSide/2;
        return new Coordinates(randX, randY);
    }
}