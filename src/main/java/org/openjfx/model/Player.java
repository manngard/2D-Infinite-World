package org.openjfx.model;


//  This is the Player class, with variables related to the player . Obs: Should also inherit Interfaces in the future
public class Player extends Combatant {
    private final int baseAtk = 20;
    private int exp = 0;
    private Item[] inventory = new Item[4];
    private int selectedItem = 0;

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
        this.atk = baseAtk;
        this.def = 0;
        itemBonus(inventory[selectedItem -1]);
    }

    public Player(String ID, double XCOORD, double YCOORD, int HP, int ATK, float ATKRANGE, int def) {
        super(ID, XCOORD, YCOORD, HP, ATK, ATKRANGE, def);
    }

    public String getId() {
        return id;
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

    public void itemBonus(Item item) {

        if(item instanceof Weapon){
            this.atk += ((Weapon) item).getAtk();
        }
        else if(item instanceof Armor){
            this.def += ((Armor) item).getDef();
        }

    }

    public boolean canAttack(){
        if (attackCooldownTicker > 0){
            return true;
        }
        attackCooldownTicker++;
        return false;
    }
}


