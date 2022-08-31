package com.compScience.game.entities;

public class Wolf extends Entity {

    private final double customXPAmount;

    public Wolf(int level, double customMoneyDropAmount) {
        super("Wolf", 9 + level, 15 + 3 * level, level, customMoneyDropAmount);
        this.customXPAmount = level * 1.30;
    }
}
