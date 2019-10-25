package org.openjfx.model.entity;

import org.openjfx.model.entity.Entity;
import org.openjfx.model.entity.Movable;

public abstract class Combatant extends Entity implements Movable {
    protected int hp;
    protected int atk;
    protected int def;
    protected final double atkRange;

    protected double moveSpeed = 1;
    protected int attackCooldownTicker = 20;

    protected Direction direction = Direction.LEFT;

    protected Combatant(String id, double x, double y, int hitpoints, int attack, double attackRange, int defense){
        super(id, x, y);
        this.hp = hitpoints;
        this.atk = attack;
        this.atkRange = attackRange;
        this.def = defense;

    }

    public int getDef() {
        return def;
    }

    public abstract boolean canAttack(); //Decides if the Combatant is allowed to attack another unit

    public void setAttackOnCooldown(){ //Sets the Combatants ability to attack on Cooldown
        attackCooldownTicker = 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public double getAtkRange() {
        return atkRange;
    }

    public void decHp(int decAmount) {

        hp = hp - decAmount;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction direction){
        switch(direction){
            case UP:
                coords.decyCoord(moveSpeed);
                this.direction = Direction.UP;
                break;

            case DOWN:
                coords.incyCoord(moveSpeed);
                this.direction = Direction.DOWN;
                break;

            case LEFT:
                coords.decxCoord(moveSpeed);
                this.direction = Direction.LEFT;
                break;

            case RIGHT:
                coords.incxCoord(moveSpeed);
                this.direction = Direction.RIGHT;
                break;
        }
    }
}