package org.openjfx.model;

public class Enemy extends Combatant {

    public Enemy(String ID, double XCOORD, double YCOORD, int HP, int ATK,float ATKRANGE){

        super(ID,XCOORD,YCOORD, HP, ATK, ATKRANGE);
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public float getAtkRange() { return atkRange; }

    public void decHp(int decAmount) {

        hp = hp - decAmount;
    }

    public void incHp(int incAmount){

        hp = hp + incAmount;

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
