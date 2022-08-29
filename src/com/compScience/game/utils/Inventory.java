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
        potionInInventory.add(new Potion("Potion of Healing", 10, 5, 1, inventoryOwner));
    }


    public void showInventoryContent() {
        System.out.println("====================");
        //Loop for showing default items
        System.out.println("Your Items:");
        if (!itemInInventory.isEmpty()) {
            for (int i = 0; i < itemInInventory.size(); i++) {
                System.out.println(i+1 + ". " + itemInInventory.get(i).getName());
            }
        }
        //Loop for showing potions
        if (!potionInInventory.isEmpty()) {
            System.out.println("====================");
            System.out.println("Your Potions:");
            for (int i = 0; i < potionInInventory.size(); i++) {
                System.out.println(i+1 + ": " + potionInInventory.get(i).getCustomNameWithLevel());
            }
        }
        System.out.println("====================");
    }

    public ArrayList<Potion> getPotionInInventory() {
        return potionInInventory;
    }
}
