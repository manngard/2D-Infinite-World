package org.openjfx.model;

public abstract class Combatant extends Entity {

    protected int hp;
    protected int atk;
    protected  float atkRange;

    public Combatant(String i, int a, int b, int HP, int ATK, float ATKRANGE){
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
}
