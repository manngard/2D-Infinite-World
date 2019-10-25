package org.openjfx.model;

class ChestFactory {
    public Chest generateChest(int spawnAreaSide) {
        Coordinates coordinates = Coordinates.generateRandomCoordinates(spawnAreaSide);
        return new Chest("Chest",coordinates.getxCoord(),coordinates.getyCoord());
    }
}
