package org.openjfx.model;

public class Enemy extends Combatant implements Movable{

    public Enemy(String ID, int XCOORD, int YCOORD, int HP, int ATK,float ATKRANGE){

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
