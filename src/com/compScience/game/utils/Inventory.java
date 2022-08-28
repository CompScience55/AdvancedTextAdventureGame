package com.compScience.game.utils;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.HealingPotion;

public class Inventory {

    //Inventory HashMap


    //Owner
    private Player inventoryOwner;

    public Inventory() {
        HealingPotion potion = new HealingPotion("Potion of Healing", 10, 3);


    }


    public void showInventoryContent() {
        System.out.println("====================");

        System.out.println("====================");
    }

    //Add consume logic for items like potions

}
