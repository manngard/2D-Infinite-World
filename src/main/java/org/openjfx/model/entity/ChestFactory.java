package org.openjfx.model.entity;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class ChestFactory {

    /*Generates a Chest with 4 random Items that has coordinates within a Square that has
    center in (0,0) and side spawnAreaSide*/

    public Chest generateChest(int spawnAreaSide) {
        Coordinates coordinates = Coordinates.generateRandomCoordinates(spawnAreaSide);
        return new Chest("Chest",coordinates.getXCoord(),coordinates.getYCoord());
    }
}
