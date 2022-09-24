package com.compScience.game.entities.items;

import com.compScience.game.entities.Entity;
import com.compScience.game.entities.Player;
import com.compScience.game.utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ItemDropper {

    private HashMap<String, ArrayList<Item>> mobItems = new HashMap<>();

    //Slime
    private ArrayList<Item> commonSlime = new ArrayList<>();
    private ArrayList<Item> uncommonSlime = new ArrayList<>();
    private ArrayList<Item> rareSlime = new ArrayList<>();
    private ArrayList<Item> epicSlime = new ArrayList<>();

    //Snake
    private ArrayList<Item> commonSnake = new ArrayList<>();
    private ArrayList<Item> uncommonSnake = new ArrayList<>();
    private ArrayList<Item> rareSnake = new ArrayList<>();
    private ArrayList<Item> epicSnake = new ArrayList<>();

    //EarthGolem
    private ArrayList<Item> commonEarthGolem = new ArrayList<>();
    private ArrayList<Item> uncommonEarthGolem = new ArrayList<>();
    private ArrayList<Item> rareEarthGolem = new ArrayList<>();
    private ArrayList<Item> epicEarthGolem = new ArrayList<>();

    //Bandit
    private ArrayList<Item> commonBandit = new ArrayList<>();
    private ArrayList<Item> uncommonBandit = new ArrayList<>();
    private ArrayList<Item> rareBandit = new ArrayList<>();
    private ArrayList<Item> epicBandit = new ArrayList<>();

    private Random r = new Random();

    private Entity e;

    public ItemDropper(Entity e) {
        this. e = e;
    }


    //Slime Entity:
    public void createSlimeItems(){
        //common
        createMobItem("Stone", 1, "Material", 0.1, commonSlime, "COMMONSlime");
        //medium
        createMobItem("Slime", 1, "Material", 3, uncommonSlime, "UNCOMMONSlime");
        //rare
        createMobItem("Dirt", 2, "Material", 0.1, rareSlime, "RARESlime");
        //epic
        createMobItem("Gold", 1, "Material", 10, epicSlime, "EPICSlime");
    }

    //Snake Entity:
    private void createSnakeItems(){
        //common
        createMobItem("Dirt", 1, "Material", 0.1, commonSnake, "COMMONSnake");
        //medium
        createMobItem("Snake Skin", 1, "Material", 2, uncommonSnake, "UNCOMMONSnake");
        //rare
        createMobItem("Snake Bones", 2, "Material", 2.5, rareSnake, "RARESnake");
        //epic
        createMobItem("Snake Tooth", 1, "Material", 5, epicSnake, "EPICSnake");
    }

    //EarthGolem Entity:
    private void createEarthGolemItems(){
        //common
        createMobItem("Dirt", 4, "Material", 0.1, commonEarthGolem, "COMMONEarthGolem");
        //medium
        createMobItem("Stone", 2, "Material", 0.1, uncommonEarthGolem, "UNCOMMONEarthGolem");
        //rare
        createMobItem("Gold", 1, "Material", 10, rareEarthGolem, "RAREEarthGolem");
        //epic
        createMobItem("Meteorite", 1, "Material", 20, epicEarthGolem, "EPICEarthGolem");
    }

    //EarthGolem Entity:
    private void createBanditItems(){
        //common
        createMobItem("Old Dagger", 1, "Weapon", 2, commonBandit, "COMMONBandit");
        //medium
        createMobItem("Old Leggings", 2, "Armor", 1.5, uncommonBandit, "UNCOMMONBandit");
        //rare
        createMobItem("Jewels", 1, "Material", 8, rareBandit, "RAREBandit");
        //epic
        createMobItem("A Bag full of Coins.", 1, "Material", 25, epicBandit, "EPICBandit");
    }

    private void createMobItem(String name, int amount, String identifier, double howMuchCoinsWorth, ArrayList<Item> itemArrayList, String rarityIdentifier) {
        Item item = new Item(name, howMuchCoinsWorth, amount, identifier);
        itemArrayList.add(item);
        mobItems.put(rarityIdentifier, itemArrayList);
    }

    private Enum getItemRarity() {
        int random = r.nextInt(100) + 1;
        if (random <= 50) {
            return ItemRarity.COMMON;
        } else if (random <= 75) {
            return ItemRarity.UNCOMMON;
        } else if (random <= 95) {
            return ItemRarity.RARE;
        } else {
            return ItemRarity.EPIC;
        }
    }
    public void dropRandomItem(Player player) {
        createEntityItems(e.getName());
        String identifier = getItemRarity() + e.getName();
        ArrayList<Item> itemsToDrop = mobItems.get(identifier);
        //zufälliges Item auswählen
        player.getPlayerInventory().getItemInInventory().add(itemsToDrop.get(new Random().nextInt(itemsToDrop.size())));
    }

    private void createEntityItems (String entityName){
        switch (entityName) {
            case "Slime":
                createSlimeItems();
                break;
            case "Snake":
                createSnakeItems();
                break;
            case "EarthGolem":
                createEarthGolemItems();
                break;
            case "Bandit":
                createBanditItems();
                break;
        }
    }
}
