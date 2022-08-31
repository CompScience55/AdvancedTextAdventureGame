package com.compScience.game.entities.mountains;

import com.compScience.game.entities.Entity;

public class Ghost extends Entity {

    private final double customXPAmount;

    public Ghost(int level, double customMoneyDropAmount) {
        super("Ghost", level, 1.25 * level, level, customMoneyDropAmount);
        this.customXPAmount = level * 4.5;
    }
}
