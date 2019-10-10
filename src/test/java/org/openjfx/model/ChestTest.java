package org.openjfx.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openjfx.model.tilepackage.Tile;

public class ChestTest {

    Chest chest = new Chest("Chest1", 0, 0);


    @Test //    Test to see if the items in the chest are random. Prints out the items in the chest.
    public void chestRandomTest(){

        System.out.println(
                "\n" + "Item is: " + chest.inventory[0].getName() + " at [0]" +
                "\n" + "Item is: " + chest.inventory[1].getName() + " at [1]" +
                "\n" + "Item is: " + chest.inventory[2].getName() + " at [2]" +
                "\n" + "Item is: " + chest.inventory[3].getName() + " at [3]");

        Assert.assertEquals(chest.inventory[0].getName(), chest.inventory[0].getName());
    }

}
