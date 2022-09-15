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
    //Attacks
    private ArrayList<Attack> playerAttacks = new ArrayList<>();

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
        magicSpells.add(new MagicSpell("Fire", 20, 12, this, 25, 30));
        playerAttacks.add(new Attack("Kick", damagePoints+3));
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
        return handleAttackOptionsInput(attackOptionIndex, e, game, scanner);
    }

    public void showPlayerAttacks() {
        System.out.println("====================");
        for (int i = 0; i < playerAttacks.size(); i++) {
            System.out.println(i+1 + ": " + playerAttacks.get(i).getName());
        }
    }

    public int getPlayerAttackMenuInput() {
        System.out.println("====================");
        System.out.println("Which attack do you want to use?");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int attackIndex = scanner.nextInt();
            attackIndex--;
            return attackIndex;
        } else {
            System.out.println("Use digits like '1'!");
            return 9999;
        }

    }
    public boolean handleAttackOptionsInput(int index, Entity e, AdventureGame game, Scanner scanner) {
        if (index == 1)  {
            Attack attack = new Attack("Punch", damagePoints);
            showPlayerAttacks();
            int attackIndex = getPlayerAttackMenuInput();
            if (attackIndex != 9999) {
                attack = playerAttacks.get(attackIndex);
            }

            System.out.println("You attack a " + e.getEntityName() + ".");
            int randomHitChanceIndex = r.nextInt(100) + 1;
            int randomCriticalChanceIndex = r.nextInt(100)+1;

            //Hit missed
            if (randomHitChanceIndex <= 15) {
                System.out.println("Your attack was blocked by your enemy!");
                return true;
            } else if (attack != null){
                attack.useAttackOnEntity(this, e);
            }
        }
        else  {
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
