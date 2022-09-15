package com.compScience.game.entities.forest;

import com.compScience.game.entities.Entity;

public class Bear extends Entity {

    private final double customXPAmount;

    public Bear(int level, double customMoneyDropAmount) {
        super("Bear", 10 + 0.2 * level, 15 + 3 * level, level, customMoneyDropAmount);
        this.customXPAmount = level*6;
    }
}
