package com.compScience.game.entities;

public class Attack {

    private String attackName;
    private double attackDamage;

    public Attack(String name, double attackDamage) {
        this.attackDamage = attackDamage;
        this.attackName = name;
    }

    public void useAttackOnPlayer(Player player, BossEntity attackUser) {
        System.out.println("====================");
        System.out.println(attackUser.getEntityName() + " attacks you with " + attackName + "!");
        System.out.println("You get " + attackDamage + " HP damage.");
        player.setHealthPoints(player.getHealthPoints() - attackDamage);
        System.out.println("====================");
    }

    public void useAttackOnBoss(Player player, BossEntity attackReceiver) {
        System.out.println("====================");
        System.out.println("You use " + attackName + "!");
        System.out.println("You deal " + attackDamage + " HP damage.");
        attackReceiver.setBossHp(attackReceiver.getHealthPoints() - attackDamage);
        System.out.println("====================");
    }

    public void useAttackOnEntity(Player player, Entity attackReceiver) {
        System.out.println("====================");
        System.out.println("You use " + attackName + "!");
        System.out.println("You deal " + attackDamage + " HP damage.");
        attackReceiver.setHealthPoints(attackReceiver.getHealthPoints() - attackDamage);
        System.out.println("====================");
    }

    public String getName() {
        return attackName;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public double getAttackDamage() {
        return attackDamage;
    }
}
