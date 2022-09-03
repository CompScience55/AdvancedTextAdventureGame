package com.compScience.game.entities.npcs;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;

import java.util.Scanner;

public class Alchemist extends NPC{

    Scanner scanner = new Scanner(System.in);

    public Alchemist(String name, Player player) {
        super(name);
    }

    public void showAlchemistInventory(Player player) {
        boolean isDone = true;
        while (isDone) {
            System.out.println("You meet a old man. He speaks to you.");
            System.out.println("====================");
            System.out.println("Alchemist: Hello Traveler, do you need some potions?");
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
                            isDone = processPotionInventory(player);
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

    public void showPotionShopOptions() {
        System.out.println("1: [LVL 1] Potion of Healing | Cost: 10");
        System.out.println("2: [LVL 3] Potion of Healing | Cost: 30");
        System.out.println("3: [LVL 1] Mana Potion | Cost: 15");
        System.out.println("4: [LVL 3] Mana Potion | Cost: 35");
        System.out.println("5: Exit");
    }

    public boolean processPotionInventory(Player player) {
        System.out.println("====================");
        System.out.println("Alchemist: What do you need?");
        System.out.println("====================");

        showPotionShopOptions();

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();

            if (input < 6 && input > 0) {

                switch (input) {
                    case 1: {
                        Potion potion = new Potion("Potion of Healing", 10, 1, 1, player);
                        boolean hasEnoughCoins = editPlayersMoneyAfterTransaction(player, potion);
                        if (hasEnoughCoins) {
                            addPotionToPlayersInventory(player, potion);
                            System.out.println("You bought a [LVL 1] Potion of Healing for 10 coins!");
                        } else {
                            System.out.println("Alchemist: Hmmm, seems like you're not worthy of my craft.");
                        }
                        break;
                    }
                    case 2: {
                        Potion potion = new Potion("Potion of Healing", 30, 1, 3, player);
                        boolean hasEnoughCoins = editPlayersMoneyAfterTransaction(player, potion);
                        if (hasEnoughCoins) {
                            addPotionToPlayersInventory(player, potion);
                            System.out.println("You bought a [LVL 3] Potion of Healing for 30 coins!");
                        } else {
                            System.out.println("Alchemist: Hmmm, seems like you're not worthy of my craft.");
                        }
                        break;
                    }
                    case 3: {
                        Potion potion = new Potion("Mana Potion", 15, 1, 1, player);
                        boolean hasEnoughCoins = editPlayersMoneyAfterTransaction(player, potion);
                        if (hasEnoughCoins) {
                            addPotionToPlayersInventory(player, potion);
                            System.out.println("You bought a [LVL 1] Mana Potion for 15 coins!");
                        } else {
                            System.out.println("Alchemist: Hmmm, seems like you're not worthy of my craft.");
                        }
                        break;
                    }
                    case 4: {
                        Potion potion = new Potion("Mana Potion", 35, 1, 3, player);
                        boolean hasEnoughCoins = editPlayersMoneyAfterTransaction(player, potion);
                        if (hasEnoughCoins) {
                            addPotionToPlayersInventory(player, potion);
                            System.out.println("You bought a [LVL 3] Mana Potion for 35 coins!");
                        } else {
                            System.out.println("Alchemist: Hmmm, seems like you're not worthy of my craft.");
                        }
                        break;
                    }
                }

            } else {
                System.out.println("Use the options above!");
            }
            if (5 == input) {
                return false;
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return false;
    }

    public void addPotionToPlayersInventory(Player player, Potion potion) {
        //Get ArrayList and see if potion already exists. If true increase number
        //Player array via functions
        int potionAmount;
        int potionNotInsideArray = 1;
        for (int i = 0; i < player.getPlayerInventory().getPotionInInventory().size(); i++) {
            if (player.getPlayerInventory().getPotionInInventory().get(i).getName().equals(potion.getName()) && player.getPlayerInventory().getPotionInInventory().get(i).getPotionLevel() == potion.getPotionLevel()) {
                potionNotInsideArray = 2;
                player.getPlayerInventory().getPotionInInventory().get(i).setPotionAmount(player.getPlayerInventory().getPotionInInventory().get(i).getPotionAmount() + 1);
                System.out.println("Potion number added Test");
                break;
            }
        }

        if (potionNotInsideArray == 1) {
            player.getPlayerInventory().getPotionInInventory().add(potion);
            System.out.println("Potion added Test");
        }
    }

    public boolean editPlayersMoneyAfterTransaction(Player player, Potion potion) {
        if (player.getMoneyCounter() >= potion.getHowMuchCoinsWorth()) {
            player.setMoneyCounter(player.getMoneyCounter() - potion.getHowMuchCoinsWorth());
            return true;
        } else {
            return false;
        }
    }
}
