package org.openjfx.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openjfx.model.tilepackage.Tile;

public class ChestTest {


    private Chest chest = new Chest("Chest",1,1);

    @Test
    public void inventoryTest(){
        Assert.assertEquals(chest.getInventorySize(),4);
        for (int i = 0; i<chest.getInventorySize(); i++){
            Assert.assertTrue(chest.getItem(i).getId() == "Armor" || chest.getItem(i).getId() == "Sword" || chest.getItem(i).getId() == "Axe");

        }
    }
}
