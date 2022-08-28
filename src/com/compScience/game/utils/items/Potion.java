package com.compScience.game.utils.items;

import com.compScience.game.entities.Player;

public class Potion {

    private String name;
    private double howMuchCoinsWorth;
    private int amount;
    private double refillAmount;
    private int potionLevel;

    private Player owner;

    public Potion(String name, double howMuchCoinsWorth, int amount, double refillAmount, int potionLevel, Player owner) {
        this.name = name;
        this. howMuchCoinsWorth = howMuchCoinsWorth;
        this.amount = amount;
        this.refillAmount = refillAmount;
        this.potionLevel = potionLevel;
        this.owner = owner;

        //edit refill amount calculation with level
    }


}
