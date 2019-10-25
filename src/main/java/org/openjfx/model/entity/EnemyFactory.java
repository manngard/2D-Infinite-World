package org.openjfx.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:Factory for enemy objects
  Used by: World
  Uses: Enemy
  */

public class EnemyFactory {
    private final List<String> enemyTypes = new ArrayList<>();

    public EnemyFactory(){
        enemyTypes.add("Goblin");
        enemyTypes.add("Rat");
    }

    /*Generates a random enemy from enemyTypes Enemy with that has coordinates within a Square that has
        center in (0,0) and side spawnAreaSide*/

    public Combatant generateEnemy(int spawnAreaSide) {
        Random rand = new Random();
        Coordinates coordinates = Coordinates.generateRandomCoordinates(spawnAreaSide);
        int randIndex = rand.nextInt(enemyTypes.size());
        return new Enemy(enemyTypes.get(randIndex),coordinates.getXCoord(),coordinates.getYCoord(),100,30,2, 0);
    }
}
