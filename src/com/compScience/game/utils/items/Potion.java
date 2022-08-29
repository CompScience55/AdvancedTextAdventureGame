package com.compScience.game.utils.items;

import com.compScience.game.entities.Player;

public class Potion {

    private String name;
    private double howMuchCoinsWorth;
    private int amount;
    private double refillAmount;
    private int potionLevel;

    //Item Identifier
    public final String identifier = "potion";

    private Player owner;

    public Potion(String name, double howMuchCoinsWorth, int amount, int potionLevel, Player owner) {
        this.name = name;
        this. howMuchCoinsWorth = howMuchCoinsWorth;
        this.amount = amount;
        this.refillAmount = 5 + 1.25 * potionLevel;
        this.potionLevel = potionLevel;
        this.owner = owner;
    }

    //Potion consume
    public void consumePotion(Potion potion) {

        switch (potion.getName()) {
            case "Potion of Healing": {
                potion.owner.setHealthPoints(potion.owner.getHealthPoints() + potion.refillAmount);
                System.out.println("You consumed a " + potion.getName() + ". You healed " + potion.refillAmount + " HP. Total HP: " + owner.getHealthPoints());
            }
        }
    }

    public String getCustomNameWithLevel() {
        return "LVL [" + potionLevel + "] " + name;
    }

    public String getName() {
        return name;
    }
}
