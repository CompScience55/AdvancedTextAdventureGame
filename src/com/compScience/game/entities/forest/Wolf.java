package com.compScience.game.entities.forest;

import com.compScience.game.entities.Entity;

public class Wolf extends Entity {

    private final double customXPAmount;

    public Wolf(int level, double customMoneyDropAmount) {
        super("Wolf", 9 + level, 16 +  level, level, customMoneyDropAmount);
        this.customXPAmount = level * 3;
    }
}
