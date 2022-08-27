package com.compScience.game.entities;

public class Entity {

    //Entity Attributes
    private String entityName;
    private double damagePoints;
    private double healthPoints;
    private int entityLevel;
    private final double customXPAmount;

    public Entity(String name, double damagePoints, double healthPoints, int entityLevel) {
        this.customXPAmount = entityLevel * 1.25;
        this.entityName = name;
        this.damagePoints = 8;
        this.healthPoints = healthPoints;
        this.entityLevel = entityLevel;
    }

    public Entity() {
        this.customXPAmount = entityLevel * 1.25;
    }

    public boolean checkForEntityDeath(Player player) {
        if (healthPoints <= 0) {
            System.out.println("You slayed a " + getEntityName() + ".");
            System.out.println("You received " + customXPAmount + " XP.");
            player.setCurrentXpProgressionCounter(player.getCurrentXpProgressionCounter() + customXPAmount);
            player.checkForLevelUp();
            return false;
        }
        return true;
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
}
