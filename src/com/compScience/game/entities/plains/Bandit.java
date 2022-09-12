package com.compScience.game.entities.plains;

import com.compScience.game.entities.Entity;

public class Bandit extends Entity {

    private final double customXPAmount;

    public Bandit(int level, double customMoneyDropAmount) {
       super("Bandit", 5 + 2*level, 8 + 2 * level, level, customMoneyDropAmount);
        this.customXPAmount = level*3;
    }
}
