package com.compScience.game.utils.items;

import com.compScience.game.utils.Item;

public class HealingPotion extends Item {

    //Item attributes
    double healAmount;
    int potionLevel;

    public HealingPotion(String name, double howMuchCoinsWorth, int itemAmount) {
        super(name, howMuchCoinsWorth, itemAmount);
        this.healAmount = 5 + potionLevel * 1.5;
        this.potionLevel = 1;
    }

    public double getHealAmount() {
        return healAmount;
    }
}
