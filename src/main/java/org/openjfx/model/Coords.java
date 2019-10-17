package org.openjfx.model;

public class Coords {
    double xCoord;
    double yCoord;

    public Coords(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }
}
