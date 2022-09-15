package com.compScience.game.entities.mountains;

import com.compScience.game.entities.Entity;

public class Werewolf extends Entity {

    private final double customXPAmount;

    public Werewolf(int level, double customMoneyDropAmount) {
        super("Werewolf", 6 + 1.2*level, 25 + level, level, customMoneyDropAmount);
        this.customXPAmount = level * 7;
    }
}
