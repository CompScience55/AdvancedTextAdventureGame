package com.compScience.game.entities.npcs;

import com.compScience.game.entities.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Wizard extends NPC{

    Scanner scanner = new Scanner(System.in);

    public Wizard(String name, Player player) {
        super(name);
    }

    public void showWizardInventory(Player player) {
        boolean isDone = true;
        while (isDone) {
            System.out.println("You meet a man in a robe. You speak to him.");
            System.out.println("====================");
            System.out.println("You: Hello, who are you?");
            System.out.println("Wizard: I'm a wizard living in this forest. Do you want to buy some spell scrolls?");
            System.out.println("====================");
            System.out.println("1: Yes");
            System.out.println("2: No");

            boolean correctInput = true;

            while (correctInput) {
                if (scanner.hasNextInt()) {
                    correctInput = false;
                    boolean correctDigits = true;
                    while (correctDigits) {
                        int input = scanner.nextInt();
                        if (input == 1) {
                            correctDigits = false;
                            isDone = processSpellInventory(player);
                        } else if (input == 2) {
                            correctDigits = false;
                            isDone = false;
                        } else
                            System.out.println("Use '1' or '2'!");
                    }
                } else {
                    System.out.println("Use digits like '1'!");
                }
            }
        }
    }

    public void showSpellShopOptions() {
        System.out.println("1: Upgrade Spell Scroll");
        //Other Spells
        System.out.println("2: Exit");
    }

    public boolean processSpellInventory(Player player) {
        System.out.println("====================");
        System.out.println("Wizard: I can offer you those spells.");
        System.out.println("====================");

        showSpellShopOptions();

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();

            if (input < 3 && input > 0) {

                switch (input) {
                    case 1: {
                        System.out.println("Wizard: Which spell do you want to upgrade?");
                        System.out.println("====================");

                        //Spells
                        for (int i = 0; i < player.getMagicSpells().size(); i++) {
                            System.out.println(i+1 + ": " + player.getMagicSpells().get(0).getName() + " Spell | Cost: " + player.getMagicSpells().get(0).getSpellUpgradeCost());
                        }
                        System.out.println("====================");

                        ArrayList<Integer> possibleChoices = new ArrayList<Integer>();
                        possibleChoices.add(1);
                        int spellInput = checkForCorrectUserInputInMenus(possibleChoices);

                        double spellUpgradeCost = player.getMagicSpells().get(spellInput-1).getSpellUpgradeCost();
                        if (spellUpgradeCost <= player.getMoneyCounter()) {
                            player.getMagicSpells().get(spellInput-1).upgradeSpell();
                            System.out.println("You read the scroll and increase your spell ability.");
                            player.setMoneyCounter(player.getMoneyCounter() - spellUpgradeCost);
                        } else {
                            System.out.println("Wizard: You don't have enough coins.");
                            System.out.println("Wizard: Give me back my scroll and screw you!");
                        }
                        break;
                    }

                }

            } else {
                System.out.println("Use the options above!");
            }
            if (2 == input) {
                return false;
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return false;
    }

    public int checkForCorrectUserInputInMenus(ArrayList<Integer> possibleChoices) {
        if (scanner.hasNextInt()) {
            int userInput = scanner.nextInt();

            if (possibleChoices.contains(userInput)) {
                return userInput;
            } else {
                System.out.println("Use the options from above!");
                return 9999;
            }

        } else {
            System.out.println("Use digits like '1'!");
            return 9999;
        }
    }
}
