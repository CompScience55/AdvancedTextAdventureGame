package com.compScience.game.entities;

public class Spider extends Entity{

    private final double customXPAmount;

    public Spider(int level, double customMoneyDropAmount) {
        super("Spider", 12+0.5*level, 6+5*level, level, customMoneyDropAmount);
        this.customXPAmount = level * 1.8;
    }
}
