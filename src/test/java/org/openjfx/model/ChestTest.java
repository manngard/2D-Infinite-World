package org.openjfx.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openjfx.model.tilepackage.Tile;

public class ChestTest {


    Item[] items = {new Armor("Armor"),new Weapon("Weapon"),new Item("päron"), new Item("karta")};
    private Chest chest = new Chest("kista", 1, 1,items);

    @Test
    public void chestest(){

        Assert.assertEquals(chest.inventory[0].getName(),"Armor");
        Assert.assertEquals(chest.inventory[1].getName(),"Weapon");
        Assert.assertEquals(chest.inventory[2].getName(),"päron");
        Assert.assertEquals(chest.inventory[3].getName(),"karta");
    }
}
