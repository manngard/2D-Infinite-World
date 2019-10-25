package org.openjfx.model.entity;

public class Enemy extends Combatant {
    public Enemy(String id, double xCoord, double yCoord, int hp, int attack,float attackRange, int defense){
        super(id,xCoord,yCoord, hp, attack, attackRange, defense);
        this.moveSpeed = 0.1;
    }

    public boolean canAttack(){
        if (attackCooldownTicker > 20){
            return true;
        }
        attackCooldownTicker++;
        return false;

    }
}