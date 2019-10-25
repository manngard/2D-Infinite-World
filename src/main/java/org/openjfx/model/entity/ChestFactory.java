package org.openjfx.model.entity;

public class ChestFactory {
    public Chest generateChest(int spawnAreaSide) {
        Coordinates coordinates = Coordinates.generateRandomCoordinates(spawnAreaSide);
        return new Chest("Chest",coordinates.getXCoord(),coordinates.getYCoord());
    }
}
