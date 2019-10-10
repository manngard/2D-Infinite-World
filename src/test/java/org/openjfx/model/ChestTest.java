package org.openjfx.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openjfx.model.tilepackage.Tile;

public class ChestTest {

    Chest chest = new Chest("Chest1", 0, 0);


    @Test //    Test to see if the items in chest are random.
    public void chestRandomTest(){
        Item item = chest.inventory[1];

        if(chest.inventory[0].getName().equals("Axe")){
            System.out.println("Item is: Axe at [0]");
            Assert.assertEquals(chest.inventory[0].getName(),"Axe");
        }else if(chest.inventory[1].getName().equals("Sword")){
            System.out.println("Item is: Sword at [1]");
            Assert.assertEquals(chest.inventory[1].getName(),"Sword");

        }else if(chest.inventory[2].getName().equals("Plate armor")){
            System.out.println("Item is: Plate armor at [2]");
            Assert.assertEquals(chest.inventory[2].getName(),"Plate armor");

        }else if(chest.inventory[3].getName().equals("Cloth armor")){
            System.out.println("Item is: Cloth armor at [3]");
            Assert.assertEquals(chest.inventory[3].getName(),"Cloth armor");

        }


    }
}
