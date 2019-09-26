package org.openjfx.model;

public abstract class Combatant extends Entity implements Movable{

    protected int hp;
    protected int atk;
    protected  float atkRange;
    directions direction = directions.LEFT;

    public Combatant(String i, double a, double b, int HP, int ATK, float ATKRANGE){
        super(i, a, b);
        this .hp = HP;
        this.atk = ATK;
        this.atkRange = ATKRANGE;

    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
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

    public void moveLeft(){};
    public void moveRight(){};
    public void moveUp(){};
    public void moveDown(){};
}
