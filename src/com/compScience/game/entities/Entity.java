package com.compScience.game.entities;

import com.compScience.game.AdventureGame;
import com.compScience.game.utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Entity {

    //Entity Attributes
    private String entityName;
    private double damagePoints;
    private double healthPoints;
    private int entityLevel;
    private final double customXPAmount;
    private double customMoneyDropAmount;

    private HashMap<String, ArrayList<Item>> mobItems = new HashMap<>();

    //Slime
    ArrayList<Item> commonSlime = new ArrayList<>();
    ArrayList<Item> mediumSlime = new ArrayList<>();
    ArrayList<Item> rareSlime = new ArrayList<>();
    ArrayList<Item> epicSlime = new ArrayList<>();

    //Snake
    ArrayList<Item> commonSnake = new ArrayList<>();
    ArrayList<Item> mediumSnake = new ArrayList<>();
    ArrayList<Item> rareSnake = new ArrayList<>();
    ArrayList<Item> epicSnake = new ArrayList<>();

    //EarthGolem
    ArrayList<Item> commonEarthGolem = new ArrayList<>();
    ArrayList<Item> mediumEarthGolem = new ArrayList<>();
    ArrayList<Item> rareEarthGolem = new ArrayList<>();
    ArrayList<Item> epicEarthGolem = new ArrayList<>();

    //Bandit
    ArrayList<Item> commonBandit = new ArrayList<>();
    ArrayList<Item> mediumBandit = new ArrayList<>();
    ArrayList<Item> rareBandit = new ArrayList<>();
    ArrayList<Item> epicBandit = new ArrayList<>();




    Random r = new Random();

    public Entity(String name, double damagePoints, double healthPoints, int entityLevel, double customMoneyDropAmount) {
        this.customXPAmount = entityLevel * 1.25;
        this.entityName = name;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.entityLevel = entityLevel;
        this.customMoneyDropAmount = customMoneyDropAmount;
    }
    public Entity() {
        this.customXPAmount = entityLevel * 1.25;
    }

    public boolean checkForEntityDeath(Player player, AdventureGame game) {
        if (healthPoints <= 0) {
            System.out.println("====================");
            System.out.println("You slayed a " + getEntityName() + ".");
            System.out.println("You received " + customXPAmount + " XP.");
            System.out.println("You received " + customMoneyDropAmount + " Coins.");
            player.setMoneyCounter(player.getMoneyCounter() + customMoneyDropAmount);
            player.setCurrentXpProgressionCounter(player.getCurrentXpProgressionCounter() + customXPAmount);
            player.checkForLevelUp();
            game.setInFight(false);
            return false;
        }
        return true;
    }

    public boolean attackPlayer(Player p, AdventureGame game) {
        System.out.println("You get attacked by the " + this.getEntityName() + ".");
        int randomHitChanceIndex = r.nextInt(100)+1;

        if (randomHitChanceIndex <= 15) {
            System.out.println("Your enemy missed the attack. You took no damage.");
            return true;
        } else {
            System.out.println("You took " + this.getDamagePoints() + " HP damage from the attack.");
            p.setHealthPoints(p.getHealthPoints() - this.getDamagePoints());
            return p.checkForPlayerDeath(game);
        }
    }

    //Slime Entity:
    public void createSlimeItems(){
        //common
        createMobItem("Stone", 1, "Material", 0.1, commonSlime, "commonSlime");
        //medium
        createMobItem("Slime", 1, "Material", 3, mediumSlime, "mediumSlime");
        //rare
        createMobItem("Dirt", 2, "Material", 0.1, rareSlime, "rareSlime");
        //epic
        createMobItem("Gold", 1, "Material", 10, epicSlime, "epicSlime");
    }

    //Snake Entity:
    public void createSnakeItems(){
        //common
        createMobItem("Dirt", 1, "Material", 0.1, commonSnake, "commonSnake");
        //medium
        createMobItem("Snake Skin", 1, "Material", 2, mediumSnake, "mediumSnake");
        //rare
        createMobItem("Snake Bones", 2, "Material", 2.5, rareSnake, "rareSnake");
        //epic
        createMobItem("Snake Tooth", 1, "Material", 5, epicSnake, "epicSnake");
    }

    //EarthGolem Entity:
    public void createEarthGolemItems(){
        //common
        createMobItem("Dirt", 4, "Material", 0.1, rareEarthGolem, "rareEarthGolem");
        //medium
        createMobItem("Stone", 2, "Material", 0.1, rareEarthGolem, "rareEarthGolem");
        //rare
        createMobItem("Gold", 1, "Material", 10, rareEarthGolem, "rareEarthGolem");
        //epic
        createMobItem("Meteorite", 1, "Material", 20, epicEarthGolem, "epicEarthGolem");
    }

    public void createMobItem(String name, int amount, String identifier, double howMuchCoinsWorth, ArrayList<Item> itemArrayList, String rarityIdentifier) {
        Item item = new Item(name, howMuchCoinsWorth, amount, identifier);
        itemArrayList.add(item);
        mobItems.put(rarityIdentifier, itemArrayList);

    }


    public String getItemRarity() {
        int random = r.nextInt(100) + 1;
        if (random <= 50) {
            return "common";
        } else if (random <= 75) {
            return "medium";
        } else if (random <= 95) {
            return "rare";
        } else {
            return "epic";
        }
    }
    public void dropRandomItem (Player player) {
        createEntityItems(entityName);
        String identifier = getItemRarity() + entityName;
        ArrayList<Item> itemsToDrop = mobItems.get(identifier);
        //zufälliges Item auswählen
        player.getPlayerInventory().getItemInInventory().add(itemsToDrop.get(new Random().nextInt(itemsToDrop.size())));
    }

    public void createEntityItems (String entityName){
        switch (entityName) {
            case "Slime":
                createSlimeItems();
                break;
            case "Snake":
                createSnakeItems();
                break;



        }
    }

    public String getEntityName() {
        return " LVL [" + entityLevel + "] " + entityName;
    }

    // Getter & Setter Health
    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public double getDamagePoints() {
        return damagePoints;
    }
}
