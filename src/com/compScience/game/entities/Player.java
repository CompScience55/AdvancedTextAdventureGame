package com.compScience.game.entities;

import com.compScience.game.AdventureGame;
import com.compScience.game.utils.Inventory;
import com.compScience.game.utils.MagicSpell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {

    //Experience System Variables
    private int playerLevel;
    private double xpBorder;
    private double currentXpProgressionCounter;

    //Player's Stats
    private double manaPoints;
    private double damagePoints;
    private double healthPoints;
    private double playerLevelHealthPoints;
    private double playerLevelManaPoints;

    //Player Utils
    private Inventory playerInventory;
    private ArrayList<MagicSpell> magicSpells = new ArrayList<>();
    //Money
    private double moneyCounter;

    //Utils
    Random r = new Random();

    public Player() {
    }

    public void createNewPlayerObject() {
        this.playerLevel = 1;
        this.currentXpProgressionCounter = 0;
        this.manaPoints = 100;
        this.xpBorder = 20;
        this.damagePoints = 6;
        this.healthPoints = 30;
        playerLevelHealthPoints = healthPoints;
        playerLevelManaPoints = manaPoints;
        this.playerInventory = new Inventory(this);
        this.moneyCounter = 30;
        //Starting Spell
        magicSpells.add(new MagicSpell("Fire", 20, 12, this));
    }

    //Using Magic
    public void showAllSpellsAvailableForPlayer() {
        System.out.println("====================");
        System.out.println("Which spell do you want to use?");
        for (int i = 0; i < magicSpells.size(); i++) {
            System.out.println(i+1 + ": " + magicSpells.get(i).getName() + " Spell | Cost in Mana: " + magicSpells.get(i).getManaCost());
        }
        System.out.println("====================");
    }

    public void processSpellSelectionInput(Scanner scanner,Entity damageTaker) {
        if (scanner.hasNextInt()) {
            int spellIndex = scanner.nextInt();
            magicSpells.get(spellIndex-1).executeMagicSpell(damageTaker, r);
        } else {
            System.out.println("Use digits like '1'!");
        }
    }


    public boolean playerAttacksOtherEntity(Scanner scanner,Entity e, int attackOptionIndex, AdventureGame game) {

            if (attackOptionIndex == 1)  {
                System.out.println("You attack a " + e.getEntityName() + ".");
                int randomHitChanceIndex = r.nextInt(100) + 1;
                int randomCriticalChanceIndex = r.nextInt(100)+1;

                //Hit missed
                if (randomHitChanceIndex <= 15) {
                    System.out.println("Your hit was blocked by your enemy!");
                    return true;
                } else {
                    //Critical Hit
                    if (randomCriticalChanceIndex <= 10) {
                        System.out.println("CRITICAL HIT! You dealt " + damagePoints*1.5 + " HP damage by punching your enemy.");
                        e.setHealthPoints(e.getHealthPoints() - damagePoints*1.5);
                        return e.checkForEntityDeath(this, game);
                    } else {
                        //Normal Hit
                        System.out.println("You dealt " + damagePoints + " HP damage by punching your enemy.");
                        e.setHealthPoints(e.getHealthPoints() - damagePoints);
                        return e.checkForEntityDeath(this, game);
                    }
                }
            }
        if (attackOptionIndex == 2)  {
                showAllSpellsAvailableForPlayer();
                processSpellSelectionInput(scanner,e);
                return e.checkForEntityDeath(this, game);
        }

        return true;
    }

    public void checkForLevelUp() {
        if (currentXpProgressionCounter >= xpBorder) {
            xpBorder += 50;
            currentXpProgressionCounter = 0;
            playerLevel++;
            damagePoints+=2;
            healthPoints+=5;
            playerLevelHealthPoints = healthPoints;
            playerLevelManaPoints = manaPoints;
            System.out.println("You level increased to " + playerLevel + "!");
        }
    }

    public boolean checkForPlayerDeath(AdventureGame game) {
        if (healthPoints <= 0) {
            System.out.println("====================");
            System.out.println("You died in combat!");
            System.out.println("You'll lose half of your coins!");
            moneyCounter /= 2;
            healthPoints = playerLevelHealthPoints;
            manaPoints = playerLevelManaPoints;
            game.setInFight(false);
            return false;
        }
        return true;
    }

    //Getter & Setter


    public double getMoneyCounter() {
        return moneyCounter;
    }

    public void setMoneyCounter(double moneyCounter) {
        this.moneyCounter = moneyCounter;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public double getCurrentXpProgressionCounter() {
        return currentXpProgressionCounter;
    }

    public void setCurrentXpProgressionCounter(double currentXpProgressionCounter) {
        this.currentXpProgressionCounter = currentXpProgressionCounter;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public double getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(double manaPoints) {
        this.manaPoints = manaPoints;
    }

    public double getPlayerLevelHealthPoints() {
        return playerLevelHealthPoints;
    }

    public double getPlayerLevelManaPoints() {
        return playerLevelManaPoints;
    }

    public ArrayList<MagicSpell> getMagicSpells() {
        return magicSpells;
    }
}
