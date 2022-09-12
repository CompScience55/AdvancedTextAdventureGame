package com.compScience.game.entities;

import com.compScience.game.AdventureGame;

import java.util.Random;

public class Entity {

    //Entity Attributes
    private String entityName;
    private double damagePoints;
    private double healthPoints;
    private int entityLevel;
    private final double customXPAmount;
    private double customMoneyDropAmount;

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

    public void showAllEntityStats() {
        System.out.println("-----------------");
        System.out.println("Stats:");
        System.out.println("HP: " + healthPoints);
        System.out.println("CustomXPAmount: " + customXPAmount);
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
