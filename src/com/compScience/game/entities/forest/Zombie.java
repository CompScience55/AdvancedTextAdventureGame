package com.compScience.game.entities.forest;

import com.compScience.game.entities.Entity;

public class Zombie extends Entity {

    private final double customXPAmount;

    public Zombie(int level, double customMoneyDropAmount) {
        super("Zombie", 2+level, 25+5*level, level, customMoneyDropAmount);
        this.customXPAmount = level * 5;
    }
}
