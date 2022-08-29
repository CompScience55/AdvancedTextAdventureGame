package com.compScience.game.utils;

public class Item {

    private String name;
    private double howMuchCoinsWorth;
    private int amount;
    public final String identifier;

    public Item(String name, double howMuchCoinsWorth, int amount, String identifier) {
        this.name = name;
        this.howMuchCoinsWorth = howMuchCoinsWorth;
        this.amount = amount;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }
}
