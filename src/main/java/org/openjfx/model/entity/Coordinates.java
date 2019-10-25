package org.openjfx.model.entity;

import java.util.Random;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public class Coordinates {
    private double xCoord;
    private double yCoord;

    public Coordinates(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /*Generates a coordinate object with random xCoord/yCoord within the area of a Square that has
    center in (0,0) and side spawnAreaSide**/

    static Coordinates generateRandomCoordinates(int spawnAreaSide){
        Random rand = new Random();
        double randX = rand.nextInt(spawnAreaSide) - spawnAreaSide /2;
        double randY = rand.nextInt(spawnAreaSide) - spawnAreaSide /2;
        return new Coordinates(randX, randY);
    }

    public double getXCoord() {
        return xCoord;
    }

    public double getYCoord() {
        return yCoord;
    }

    public void incXCoord(double d) {
        this.xCoord += d;
    }

    public void decXCoord(double d) {
        this.xCoord -= d;
    }

    public void incYCoord(double d) {
        this.yCoord += d;
    }

    public void decYCoord(double d) {
        this.yCoord -= d;
    }
}
