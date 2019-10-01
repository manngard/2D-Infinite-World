package org.openjfx.model;

public abstract class Combatant extends Entity implements Movable{

    protected int hp;
    protected int atk;
    protected  float atkRange;
    Direction direction = Direction.LEFT;

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

    @Override
    public void move(Direction direction){
        switch(direction){
            case UP:
                this.ycoord -= 1;
                this.direction = Direction.UP;
                break;

            case DOWN:
                this.ycoord += 1;
                this.direction = Direction.DOWN;
                break;

            case LEFT:
                this.xcoord -= 1;
                this.direction = Direction.LEFT;
                break;

            case RIGHT:
                this.xcoord += 1;
                this.direction = Direction.RIGHT;
                break;
        }
    }
}
