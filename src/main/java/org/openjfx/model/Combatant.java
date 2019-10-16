package org.openjfx.model;

public abstract class Combatant extends Entity implements Movable{
    protected int hp;
    protected int atk;
    protected double atkRange;
    protected double moveSpeed = 1;
    protected int attackCooldownTicker = 0;
    Direction direction = Direction.LEFT;

    public Combatant(String i, double a, double b, int HP, int ATK, double ATKRANGE){
        super(i, a, b);
        this .hp = HP;
        this.atk = ATK;
        this.atkRange = ATKRANGE;

    }

    public abstract boolean canAttack(); //Decides if the Combatant is allowed to attack another unit

    public void setAttackOnCooldown(){ //Sets the Combatants ability to attack on Cooldown
        attackCooldownTicker = 0;
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

    public void incHp(int incAmount){

        hp = hp + incAmount;

    }

    @Override
    public void move(Direction direction){
        switch(direction){
            case UP:
                this.ycoord -= moveSpeed;
                this.direction = Direction.UP;
                break;

            case DOWN:
                this.ycoord += moveSpeed;
                this.direction = Direction.DOWN;
                break;

            case LEFT:
                this.xcoord -= moveSpeed;
                this.direction = Direction.LEFT;
                break;

            case RIGHT:
                this.xcoord += moveSpeed;
                this.direction = Direction.RIGHT;
                break;
        }
    }
}
