package org.openjfx.model.entity;

import java.util.Random;

public class Coordinates {
    private double xCoord;
    private double yCoord;

    public Coordinates(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public static Coordinates generateRandomCoordinates(int spawnAreaSide){
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

    public void incxCoord(double d) {
        this.xCoord += d;
    }

    public void decxCoord(double d) {
        this.xCoord -= d;
    }

    public void incyCoord(double d) {
        this.yCoord += d;
    }

    public void decyCoord(double d) {
        this.yCoord -= d;
    }
}
