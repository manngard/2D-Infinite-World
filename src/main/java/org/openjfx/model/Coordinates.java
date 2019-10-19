package org.openjfx.model;

public class Coordinates {
    double xCoord;
    double yCoord;

    public Coordinates(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }
}
