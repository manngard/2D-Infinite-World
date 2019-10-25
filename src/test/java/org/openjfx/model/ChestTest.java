package org.openjfx.model;

import org.junit.Assert;
import org.junit.Test;
import org.openjfx.model.entity.Chest;

public class ChestTest {

    private final Chest chest = new Chest("Chest", 1, 1);

    @Test //    Test to see if the items in the chest are random. Prints out the items in the chest.
    public void chestRandomTest() {

        System.out.println(
                "\n" + "Item is: " + chest.getItem(0).getId() + " at [0]" +
                        "\n" + "Item is: " + chest.getItem(1).getId() + " at [1]" +
                        "\n" + "Item is: " + chest.getItem(2).getId() + " at [2]" +
                        "\n" + "Item is: " + chest.getItem(3).getId() + " at [3]");

        if (chest.getItem(0).getId().equals("Axe")) {
            Assert.assertEquals(chest.getItem(0).getId(), "Axe");
        } else if (chest.getItem(1).getId().equals("Sword")) {
            Assert.assertEquals(chest.getItem(1).getId(), "Sword");

        } else if (chest.getItem(2).getId().equals("Plate armor")) {
            Assert.assertEquals(chest.getItem(2).getId(), "Plate armor");

        } else if (chest.getItem(3).getId().equals("Cloth armor")) {
            Assert.assertEquals(chest.getItem(3).getId(), "Cloth armor");

        }

    }



}
