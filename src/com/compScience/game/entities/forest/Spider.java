package com.compScience.game.entities.forest;

import com.compScience.game.entities.Entity;

public class Spider extends Entity {

    private final double customXPAmount;

    public Spider(int level, double customMoneyDropAmount) {
        super("Spider", 7+0.5*level, 6+5*level, level, customMoneyDropAmount);
        this.customXPAmount = level * 1.5;
    }
}
