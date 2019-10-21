package org.openjfx.model;

public class Enemy extends Combatant {
    public Enemy(String ID, double XCOORD, double YCOORD, int HP, int ATK,float ATKRANGE, int def){

        super(ID,XCOORD,YCOORD, HP, ATK, ATKRANGE, def);
        this.moveSpeed = 0.125;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public double getAtkRange() { return atkRange; }

    public void decHp(int decAmount) {

        hp = hp - decAmount;
    }

    public void incHp(int incAmount){

        hp = hp + incAmount;

    }

    public boolean canAttack(){
        if (attackCooldownTicker > 20){
            return true;
        }
        attackCooldownTicker++;
        return false;

    }
}
