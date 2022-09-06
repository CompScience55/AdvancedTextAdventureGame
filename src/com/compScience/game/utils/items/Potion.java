package com.compScience.game.utils.items;

import com.compScience.game.entities.Player;

import java.io.Serializable;

public class Potion implements Serializable {

    private String name;
    private double howMuchCoinsWorth;
    private int potionAmount;
    private double refillAmount;
    private int potionLevel;

    //Item Identifier
    public final String identifier = "potion";

    private Player owner;

    public Potion(String name, double howMuchCoinsWorth, int potionAmount, int potionLevel, Player owner) {
        this.name = name;
        this. howMuchCoinsWorth = howMuchCoinsWorth;
        this.potionAmount = potionAmount;
        this.refillAmount = 5 + 1.25 * potionLevel;
        this.potionLevel = potionLevel;
        this.owner = owner;
    }

    public Potion(String name, double howMuchCoinsWorth, int potionAmount, int potionLevel) {
        this.name = name;
        this. howMuchCoinsWorth = howMuchCoinsWorth;
        this.potionAmount = potionAmount;
        this.refillAmount = 5 + 1.25 * potionLevel;
        this.potionLevel = potionLevel;
    }

    //Potion consume
    public void consumePotion(Potion potion, Player player) {

        switch (potion.getName()) {
            case "Potion of Healing": {
                player.setHealthPoints(player.getHealthPoints() + potion.refillAmount);
                System.out.println("You consumed a " + potion.getName() + ". You healed " + potion.refillAmount + " HP. Total HP: " + owner.getHealthPoints());
                potion.potionAmount-=1;
                break;
            }
            case "Mana Potion": {
                player.setManaPoints(player.getManaPoints() + potion.refillAmount);
                System.out.println("You consumed a " + potion.getName() + ". Your Mana Points have restored by " + potion.refillAmount + " MP.");
                potion.potionAmount-=1;
                break;
            }
            //Other Potions
        }
    }

    public String getCustomNameWithLevel() {
        return "LVL [" + potionLevel + "] " + name;
    }

    public String getName() {
        return name;
    }

    public int getPotionAmount() {
        return potionAmount;
    }

    public double getHowMuchCoinsWorth() {
        return howMuchCoinsWorth;
    }

    public double getRefillAmount() {
        return refillAmount;
    }

    public void setPotionAmount(int potionAmount) {
        this.potionAmount = potionAmount;
    }

    public int getPotionLevel() {
        return potionLevel;
    }
}
