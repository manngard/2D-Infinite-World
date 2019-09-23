package org.openjfx.model;

public class Enemy extends Entity implements Movable{

    private int hp;
    private int atk;

    public Enemy(String s, int a, int b, int c, int d){
        super(s,a,b);
        this.hp = c;
        this.atk = d;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

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
