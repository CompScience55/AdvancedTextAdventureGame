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

    //Bear
    private ArrayList<Item> commonBear = new ArrayList<>();
    private ArrayList<Item> uncommonBear = new ArrayList<>();
    private ArrayList<Item> rareBear = new ArrayList<>();
    private ArrayList<Item> epicBear = new ArrayList<>();

    //Spider
    private ArrayList<Item> commonSpider = new ArrayList<>();
    private ArrayList<Item> uncommonSpider = new ArrayList<>();
    private ArrayList<Item> rareSpider = new ArrayList<>();
    private ArrayList<Item> epicSpider = new ArrayList<>();

    //Wolf
    private ArrayList<Item> commonWolf = new ArrayList<>();
    private ArrayList<Item> uncommonWolf = new ArrayList<>();
    private ArrayList<Item> rareWolf = new ArrayList<>();
    private ArrayList<Item> epicWolf = new ArrayList<>();

    //Zombie
    private ArrayList<Item> commonZombie = new ArrayList<>();
    private ArrayList<Item> uncommonZombie = new ArrayList<>();
    private ArrayList<Item> rareZombie = new ArrayList<>();
    private ArrayList<Item> epicZombie = new ArrayList<>();



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
        createMobItem("Dirt", 4, "Material", 0.1, commonEarthGolem, "COMMONEarth Golem");
        //medium
        createMobItem("Stone", 2, "Material", 0.1, uncommonEarthGolem, "UNCOMMONEarth Golem");
        //rare
        createMobItem("Gold", 1, "Material", 10, rareEarthGolem, "RAREEarth Golem");
        //epic
        createMobItem("Meteorite", 1, "Material", 20, epicEarthGolem, "EPICEarth Golem");
    }

    //Bandit Entity:
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

    private void createBearItems(){
        //common
        createMobItem("Bear Claw", 1, "Material", 2, commonBear, "COMMONBear");
        //medium
        createMobItem("Bear Fur", 1, "Material", 4, uncommonBear, "UNCOMMONBear");
        //rare
        createMobItem("Bear Head", 1, "Material", 6, rareBear, "RAREBear");
        //epic
        createMobItem("Bear Eye", 1, "Material", 11, epicBear, "EPICBear");
    }

    private void createSpiderItems(){
        //common
        createMobItem("String", 1, "Material", 0.5, commonSpider, "COMMONSpider");
        //medium
        createMobItem("Spider Eye", 1, "Material", 1, uncommonSpider, "UNCOMMONSpider");
        //rare
        createMobItem("Spider Leg", 1, "Material", 2, rareSpider, "RARESpider");
        //epic
        createMobItem("Spider's Poison", 1, "Material", 9, epicSpider, "EPICSpider");
    }

    private void createWolfItems(){
        //common
        createMobItem("Wolf Claw", 1, "Material", 2, commonWolf, "COMMONWolf");
        //medium
        createMobItem("Wolf Fur", 1, "Material", 3, uncommonWolf, "UNCOMMONWolf");
        //rare
        createMobItem("Wolf Teeth", 1, "Material", 6, rareWolf, "RAREWolf");
        //epic
        createMobItem("Wolf Head", 1, "Material", 9, epicWolf, "EPICWolf");
    }

    private void createZombieItems(){
        //common
        createMobItem("Rotten Flesh", 1, "Material", 0.5, commonZombie, "COMMONZombie");
        //medium
        createMobItem("Cloth", 1, "Material", 2, uncommonZombie, "UNCOMMONZombie");
        //rare
        createMobItem("Worn Leggings", 1, "Material", 4, rareZombie, "RAREZombie");
        //epic
        createMobItem("A Golden Watch", 1, "Material", 14, epicZombie, "EPICZombie");
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
        Item item = itemsToDrop.get(new Random().nextInt(itemsToDrop.size()));
        player.getPlayerInventory().getItemInInventory().add(item);
        System.out.println("Your enemy dropped " + item.getName() + ".");
        System.out.println("====================");
    }

    private void createEntityItems (String entityName){
        switch (entityName) {
            case "Slime":
                createSlimeItems();
                break;
            case "Snake":
                createSnakeItems();
                break;
            case "Earth Golem":
                createEarthGolemItems();
                break;
            case "Bandit":
                createBanditItems();
                break;
            case "Bear":
                createBearItems();
                break;
            case "Spider":
                createSpiderItems();
                break;
            case "Wolf":
                createWolfItems();
                break;
            case "Zombie":
                createZombieItems();
                break;
        }
    }
}
