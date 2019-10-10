package org.openjfx.model;

public class Enemy extends Combatant {

    public Enemy(String ID, double XCOORD, double YCOORD, int HP, int ATK,float ATKRANGE){

        super(ID,XCOORD,YCOORD, HP, ATK, ATKRANGE);
        this.moveSpeed = 0.125;}

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
}
