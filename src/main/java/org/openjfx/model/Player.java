package org.openjfx.model;

//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player extends Combatant {

    private int exp = 0;

    public Player(String ID, double XCOORD, double YCOORD, int HP, int ATK,float ATKRANGE) {
        super(ID, XCOORD, YCOORD, HP, ATK, ATKRANGE);
    }

    public String getId() {
        return id;
    }

    public double getXcoord() {
        return xcoord;
    }

    public double getYcoord() {
        return ycoord;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk(){
        return atk;
    }

    public float getAtkRange() {
        return atkRange;
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
        xcoord -= 1;

        direction = directions.LEFT;

    }

    @Override
    public void moveRight() {
        xcoord += 1;

        direction = directions.RIGHT;

    }

    @Override
    public void moveUp() {
        ycoord -= 1;

        direction = directions.UP;

    }

    @Override
    public void moveDown() {
        ycoord += 1;

        direction = directions.DOWN;

    }
}


