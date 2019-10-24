package org.openjfx.model;

import java.util.Random;

public class Coordinates {
    double xCoord;
    double yCoord;

    public Coordinates(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    static Coordinates generateRandomCoordinates(int spawnAreaSide){
        Random rand = new Random();
        double randX = rand.nextInt(spawnAreaSide) - spawnAreaSide /2;
        double randY = rand.nextInt(spawnAreaSide) - spawnAreaSide /2;
        return new Coordinates(randX, randY);
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }
}
