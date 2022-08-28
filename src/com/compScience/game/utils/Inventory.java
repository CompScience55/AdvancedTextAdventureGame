package com.compScience.game.utils;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;

import java.util.ArrayList;

public class Inventory {

    //Inventory HashMap
    private ArrayList<Item> itemInInventory = new ArrayList<>();
    private ArrayList<Potion> potionInInventory = new ArrayList<>();

    //Owner
    private Player inventoryOwner;

    public Inventory(Player player) {
        this.inventoryOwner = player;
        potionInInventory.add(new Potion("Potion of Healing", 10, 3, 5, 1, inventoryOwner));
    }


    public void showInventoryContent() {
        System.out.println("====================");

        System.out.println("====================");
    }

    //Add consume logic for items like potions

}
