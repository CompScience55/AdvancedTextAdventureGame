package com.compScience.game.entities.mountains;

import com.compScience.game.entities.Entity;

public class Griffin extends Entity {

    private final double customXPAmount;

    public Griffin(int level, double customMoneyDropAmount) {
        super("Griffin", 10 + level, 40 + 2 * level, level, customMoneyDropAmount);
        this.customXPAmount = level*9;
    }
}
