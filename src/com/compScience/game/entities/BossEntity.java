package com.compScience.game.entities;

import java.util.ArrayList;
import java.util.Random;

public class BossEntity {

    private double bossHp;
    private double bossDamage;
    private String bossName;
    private int bossLevel;
    private double customXpAmount;
    private double customMoneyDropAmount;

    private ArrayList<Attack> attacks = new ArrayList<>();

    public BossEntity(String bossName, double bossHp, double bossDamage, int bossLevel, String attackName1, String attackName2, String attackName3) {
        this.bossName = bossName;
        this.bossHp= bossHp;
        this.bossDamage = bossDamage;
        this.bossLevel = bossLevel;
        this.customXpAmount = bossLevel * 3;
        this.customMoneyDropAmount = bossLevel * 5;

        //Attacks
        Attack attack1 = new Attack(attackName1, bossDamage);
        Attack attack2 = new Attack(attackName2, 2 * bossDamage);
        Attack attack3 = new Attack(attackName3, 3* bossDamage);
        attacks.add(attack1);
        attacks.add(attack2);
        attacks.add(attack3);
    }

    public int chooseRandomAttack() {
        int index = new Random().nextInt(100)+1;

        if (index <= 60) {
            return 0;
        }
        if (index > 60 && index <= 95) {
            return 1;
        } else {
            return 2;
        }
    }

    public void attackPlayer(Player player) {
        int index = chooseRandomAttack();
        Attack attack = attacks.get(index);
        attack.useAttackOnPlayer(player, this);
    }

    public double getBossHp() {
        return bossHp;
    }

    public void setBossHp(double bossHp) {
        this.bossHp = bossHp;
    }

    public String getBossName() {
        return bossName;
    }


}
