package org.openjfx.model;

//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player extends Combatant {

    private int exp = 0;
    private Item[] inventory = new Item[4];


    public Player(String ID, double XCOORD, double YCOORD, int HP, int ATK,float ATKRANGE) {
        super(ID, XCOORD, YCOORD, HP, ATK, ATKRANGE);
    }

    public String getId() {
        return id;
    }

    public double getXcoord() {
        return xcoord;
    }

    public double getYcoord() {
        return ycoord;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk(){
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

    public int getExp() {
        return exp;
    }

    public void incExp(int incAmount){
        exp = exp + incAmount;
    }

    public void decExp(int decAmount){
        exp = exp - decAmount;
    }

    public Item[] getInventory() {
        Item [] inventoryCopy = new Item[4];
        for (int i = 0; i<inventory.length; i++){
            inventoryCopy[i] = inventory[i];
        }
        return inventoryCopy;
    }

    public void setItem(Item item, int index) {
        this.inventory[index] = item;
    }

    public boolean canAttack(){
        return true;
    }
}


