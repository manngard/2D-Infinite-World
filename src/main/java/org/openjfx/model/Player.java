package org.openjfx.model;

//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player implements Movable{

    private String id;
    private int xcoord, ycoord;
    private boolean isAlive;
    private int hp;
    private int exp;

    public Player(String id, int xcoord, int ycoord, boolean isAlive, int hp, int exp) {
        this.id = id;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.isAlive = isAlive;
        this.hp = hp;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public boolean getisAlive() {
        return isAlive;
    }

    public int getHp() {
        return hp;
    }

    public void decHp(int decAmount) {
        if(decAmount >= hp){
            hp = 0;
            isAlive = false;
        }
        else{
            hp = hp - decAmount;
        }
    }

    public void incHp(int incAmount){
        if(incAmount >= 100 - hp){
            hp = 100;
        }
        else{
            hp = hp + incAmount;
        }
    }

    public int getExp() {
        return exp;
    }

    public void incExp(int incAmount){
        exp = exp + incAmount;
    }

    public void decExp(int decAmount){
        exp = exp - decAmount;
    }

    @Override
    public void moveLeft() {
        xcoord -= 1;
    }

    @Override
    public void moveRight() {
        xcoord += 1;
    }

    @Override
    public void moceUp() {
        ycoord += 1;
    }

    @Override
    public void moveDown() {
        ycoord -= 1;
    }
}


