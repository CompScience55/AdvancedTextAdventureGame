package com.compScience.game.utils;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    //Inventory HashMap
    private ArrayList<Item> itemInInventory = new ArrayList<>();
    private ArrayList<Potion> potionInInventory = new ArrayList<>();

    //Owner
    private Player inventoryOwner;

    public Inventory(Player player) {
        this.inventoryOwner = player;
        potionInInventory.add(new Potion("Potion of Healing", 10, 3, 1, inventoryOwner, 1));
        potionInInventory.add(new Potion("Fire Potion", 20, 2, 1, inventoryOwner, 2));
    }


    public void showInventoryContent() {
        System.out.println("====================");
        //Loop for showing default items
        System.out.println("Item Inventory:");
        if (!itemInInventory.isEmpty()) {
            for (int i = 0; i < itemInInventory.size(); i++) {
                System.out.println(i+1 + ". " + itemInInventory.get(i).getName() + " | Anzahl: " + itemInInventory.get(i).getAmount());
            }
        }
        //Loop for showing potions
        if (!potionInInventory.isEmpty()) {
            System.out.println("====================");
            System.out.println("Potion Inventory:");
            for (int i = 0; i < potionInInventory.size(); i++) {
                if (potionInInventory.get(i) != null) {
                    System.out.println(i + 1 + ": " + potionInInventory.get(i).getCustomNameWithLevel() + " | Anzahl: " + potionInInventory.get(i).getPotionAmount());
                }
            }
        }
    }

    public ArrayList<Potion> getPotionInInventory() {
        return potionInInventory;
    }

    public void setPotionInInventory(ArrayList<Potion> potionInInventory) {
        this.potionInInventory = potionInInventory;
    }

    public ArrayList<Item> getItemInInventory() {
        return itemInInventory;
    }
}
