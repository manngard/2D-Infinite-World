package org.openjfx.model.entity;

public abstract class Combatant extends Entity implements Movable {
    int hp;
    int atk;
    int def;
    final double atkRange;

    double moveSpeed = 1;
    int attackCooldownTicker = 20;

    private Direction direction = Direction.LEFT;

    Combatant(String id, double x, double y, int hitPoints, int attack, double attackRange, int defense){
        super(id, x, y);
        this.hp = hitPoints;
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
                coords.decYCoord(moveSpeed);
                this.direction = Direction.UP;
                break;

            case DOWN:
                coords.incYCoord(moveSpeed);
                this.direction = Direction.DOWN;
                break;

            case LEFT:
                coords.decXCoord(moveSpeed);
                this.direction = Direction.LEFT;
                break;

            case RIGHT:
                coords.incXCoord(moveSpeed);
                this.direction = Direction.RIGHT;
                break;
        }
    }
}
