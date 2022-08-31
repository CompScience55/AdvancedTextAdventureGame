package com.compScience.game.entities.mountains;

import com.compScience.game.entities.Entity;

public class Goblin extends Entity {

    private final double customXPAmount;

    public Goblin(int level, double customMoneyDropAmount) {
        super("Goblin", level, 5 + level, level, customMoneyDropAmount);
        this.customXPAmount = level * 0.75;
    }
}
