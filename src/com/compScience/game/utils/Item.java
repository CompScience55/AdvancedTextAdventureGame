package com.compScience.game.utils;

public class Item {

    private String name;
    private double howMuchCoinsWorth;
    private int amount;

    public Item(String name, double howMuchCoinsWorth, int amount) {
        this.name = name;
        this.howMuchCoinsWorth = howMuchCoinsWorth;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
}
