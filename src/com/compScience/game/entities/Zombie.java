package com.compScience.game.entities;

public class Zombie extends Entity{

    private final double customXPAmount;

    public Zombie(int level, double customMoneyDropAmount) {
        super("Zombie", 4+level, 25+5*level, level, customMoneyDropAmount);
        this.customXPAmount = level * 1.6;
    }
}
