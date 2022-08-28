package com.compScience.game.utils;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.HealingPotion;

import java.util.HashMap;

public class Inventory {

    //Inventory HashMap
    private HashMap<Integer, Item> itemList = new HashMap<>();

    //Owner
    private Player inventoryOwner;

    public Inventory() {
        itemList.put(1, new HealingPotion("Potion of Healing", 10, 3));
    }

    //Add consume logic for items like potions

}
