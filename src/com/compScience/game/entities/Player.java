package com.compScience.game.entities;

import com.compScience.game.utils.Inventory;

import java.util.Random;

public class Player {

    //Experience System Variables
    private int playerLevel;
    private double xpBorder;
    private double currentXpProgressionCounter;

    //Player's Stats
    private double manaPoints;
    private double damagePoints;
    private double healthPoints;

    //Player Utils
    private Inventory playerInventory;
    //Money

    //Utils
    Random r = new Random();

    public void createNewPlayerObject() {
        this.playerLevel = 1;
        this.currentXpProgressionCounter = 0;
        this.manaPoints = 100;
        this.xpBorder = 100;
        this.damagePoints = 5;
        this.healthPoints = 25;
        this.playerInventory = new Inventory(this);
    }

    public void showAllPlayerStats() {
        System.out.println("-----------------");
        System.out.println("Stats:");
        System.out.println("HP: " + healthPoints);
        System.out.println("LVL: " + playerLevel);
        System.out.println("XPBorder: " + xpBorder);
        System.out.println("XPProgression: " + currentXpProgressionCounter);
        System.out.println("Mana: " + manaPoints);
        System.out.println("Damage: " + damagePoints);
    }

    public boolean playerAttacksOtherEntity(Entity e) {
        System.out.println("You attack a " + e.getEntityName() + ".");
        int randomHitChanceIndex = r.nextInt(100)+1;

        if (randomHitChanceIndex <= 15) {
            System.out.println("Your hit was blocked by your enemy!");
            return true;
        } else {
            System.out.println("Your enemy took " + damagePoints + " HP damage by your hit.");
            e.setHealthPoints(e.getHealthPoints() - damagePoints);
             return e.checkForEntityDeath(this);
        }
    }

    public void checkForLevelUp() {
        if (currentXpProgressionCounter >= xpBorder) {
            xpBorder += 50;
            currentXpProgressionCounter = 0;
            playerLevel++;
            damagePoints+=2;
            healthPoints+=5;
            System.out.println("You level increased to " + playerLevel + "!");
        }
    }

    //Getter & Setter


    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public double getXpBorder() {
        return xpBorder;
    }

    public void setXpBorder(double xpBorder) {
        this.xpBorder = xpBorder;
    }

    public double getCurrentXpProgressionCounter() {
        return currentXpProgressionCounter;
    }

    public void setCurrentXpProgressionCounter(double currentXpProgressionCounter) {
        this.currentXpProgressionCounter = currentXpProgressionCounter;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}
