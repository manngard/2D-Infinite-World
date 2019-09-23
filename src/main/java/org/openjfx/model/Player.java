package org.openjfx.model;

//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player extends Entity implements Movable{

    private int hp;
    private int exp;

    public Player(String id, int xcoord, int ycoord, int hp, int exp) {
        super(id, xcoord, ycoord);
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

    public int getHp() {
        return hp;
    }

    public void decHp(int decAmount) {

            hp = hp - decAmount;
    }

    public void incHp(int incAmount){

            hp = hp + incAmount;

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
        xcoord -= 10;
    }

    @Override
    public void moveRight() {
        xcoord += 10;
    }

    @Override
    public void moveUp() {
        ycoord -= 10;
    }

    @Override
    public void moveDown() {
        ycoord += 10;
    }
}


