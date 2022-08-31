package com.compScience.game.utils;

import com.compScience.game.entities.Entity;
import com.compScience.game.entities.Player;

import java.util.Random;

public class MagicSpell {

    private String name;
    private int manaCost;
    private double dealingDamage;

    private Player owner;

    public MagicSpell(String name, int manaCost, double dealingDamage, Player owner) {
        this.dealingDamage = dealingDamage;
        this.name = name;
        this.manaCost = manaCost;
        this.owner = owner;
    }

    public void executeMagicSpell(Entity damageTaker, Random random) {
        if (owner.getManaPoints() >= manaCost) {
            System.out.println("You cast a " + name + " Spell on your enemy.");
            int randomHitIndex = random.nextInt(100) + 1;

            if (randomHitIndex <= 10) {
                System.out.println("You missed your spell!");
            } else {
                System.out.println("Your spell hit the  " + damageTaker.getEntityName() + "! You dealt " + dealingDamage + " HP damage.");
                damageTaker.setHealthPoints(damageTaker.getHealthPoints() - dealingDamage);
                owner.setManaPoints(owner.getManaPoints() - manaCost);
            }
        } else {
            System.out.println("You don't have enough mana!");
        }
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }
}
