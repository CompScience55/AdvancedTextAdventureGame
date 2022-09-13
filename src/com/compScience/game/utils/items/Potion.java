package com.compScience.game.utils.items;

import com.compScience.game.AdventureGame;
import com.compScience.game.entities.Entity;
import com.compScience.game.entities.Player;

import java.io.Serializable;


public class Potion implements Serializable {

    private String name;
    private double howMuchCoinsWorth;
    private int potionAmount;
    private double refillAmount;
    private int potionLevel;

    private int potionIdentifier;

    //Item Identifier
    public final String identifier = "potion";

    private Player owner;

    public Potion(String name, double howMuchCoinsWorth, int potionAmount, int potionLevel, Player owner, int potionIdentifier) {
        this.name = name;
        this. howMuchCoinsWorth = howMuchCoinsWorth;
        this.potionAmount = potionAmount;
        this.refillAmount = 5 + 1.25 * potionLevel;
        this.potionLevel = potionLevel;
        this.owner = owner;
        this.potionIdentifier = potionIdentifier;
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
                player.setHealthPoints(player.getHealthPoints() + potion.refillAmount*potion.getPotionLevel());
                System.out.println("You consumed a " + potion.getCustomNameWithLevel() + ". You healed " + potion.refillAmount*potion.getPotionLevel() + " HP. Total HP: " + owner.getHealthPoints());
                potion.potionAmount-=1;
                break;
            }
            case "Mana Potion": {
                player.setManaPoints(player.getManaPoints() + potion.refillAmount*potion.getPotionLevel());
                System.out.println("You consumed a " + potion.getCustomNameWithLevel() + ". Your Mana Points have restored by " + potion.refillAmount*potion.getPotionLevel() + " MP.");
                potion.potionAmount-=1;
                break;
            }

            //Other Potions
        }
    }

    public void consumePotionDealingDamage(Entity entity, Potion potion, AdventureGame game){
        if (game.isInFight()) {
            switch (potion.getName()) {
                case "Fire Potion": {
                    entity.setHealthPoints(entity.getHealthPoints() - 4 * potion.getPotionLevel());
                    System.out.println("You consumed a " + potion.getName() + ". Your Enemy took " + 4 * potion.getPotionLevel() + " HP!");
                    potion.potionAmount -= 1;
                    break;
                }
            }
        } else {
            System.out.println("You can only use this potion in fights!");
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

    public int getPotionIdentifier() {
        return potionIdentifier;
    }
}
