package com.compScience.game.entities.plains;

import com.compScience.game.entities.Entity;

public class Snake extends Entity {

    private final double customXPAmount;

    public Snake(int level, double customMoneyDropAmount) {
        super("Snake", level / 2, 5 + 3 * level, level, customMoneyDropAmount);
        this.customXPAmount = level;
    }
}
