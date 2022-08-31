package com.compScience.game.entities.plains;


import com.compScience.game.entities.Entity;

public class Slime extends Entity {

    private final double customXPAmount;

    public Slime(int level, double customMoneyDropAmount) {
        super("Slime", 6+level, 15+5*level, level, customMoneyDropAmount);
        this.customXPAmount = level * 2;
    }

    /*
    public boolean attackPlayer(Player p) {
        System.out.println("You get attacked by the " + this.getEntityName() + ".");
        int randomHitChanceIndex = r.nextInt(100)+1;

        if (randomHitChanceIndex <= 15) {
            System.out.println("Your enemy missed the attack. You took no damage.");
            return true;
        } else {
            System.out.println("You took " + this.getDamagePoints() + " HP damage from the attack.");
            p.setHealthPoints(p.getHealthPoints() - this.getDamagePoints());
            return p.checkForPlayerDeath();
        }
    }
    */

}
