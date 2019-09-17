package org.openjfx;

//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player {

    String id;
    String name;
    int xcoord, ycoord;
    boolean isAlive;
    int hp;
    int exp;

    public Player(String id, String name, int xcoord, int ycoord, boolean isAlive, int hp, int exp) {
        this.id = id;
        this.name = name;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.isAlive = isAlive;
        this.hp = hp;
        this.exp = exp;
    }

    //  This method will be inherited from an interface "Movable"
    void move(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}


