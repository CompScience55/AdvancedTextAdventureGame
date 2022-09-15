package com.compScience.game.entities.plains;

import com.compScience.game.entities.Entity;

public class EarthGolem extends Entity {

    private final double customXPAmount;

    public EarthGolem(int level, double customMoneyDropAmount) {
        super("Earth Golem", 0.75*level, 30 + 3 * level, level, customMoneyDropAmount);
        this.customXPAmount = level*3.8;
    }
}
