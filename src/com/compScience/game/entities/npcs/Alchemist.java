package com.compScience.game.entities.npcs;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;

import java.util.ArrayList;
import java.util.Scanner;

public class Alchemist extends NPC{

    private ArrayList<Potion> alchemistShopInventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Alchemist(String name) {
        super(name);
        alchemistShopInventory.add(new Potion("Potion of Healing", 8, 3, 1));
        alchemistShopInventory.add(new Potion("Potion of Healing", 8 * 3, 3, 3));
        alchemistShopInventory.add(new Potion("Mana Potion", 10 * 3, 3, 3));
        alchemistShopInventory.add(new Potion("Mana Potion", 10, 3, 1));
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
                            //isDone = processPotionInventory(player);
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

    /*
    public boolean processPotionInventory(Player player) {
        System.out.println("====================");
        System.out.println("Alchemist: What do you need?");
        System.out.println("====================");

        for (int i = 0; i < alchemistShopInventory.size(); i++) {
            System.out.println(i+1 + ": " + alchemistShopInventory.get(i).getCustomNameWithLevel() + " | Cost: " + alchemistShopInventory.get(i).getHowMuchCoinsWorth());
        }
        System.out.println(alchemistShopInventory.size() + ": Exit");

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();

            if (input-1 < alchemistShopInventory.size()) {
                Potion potion = alchemistShopInventory.get(input-1);
                System.out.println("You bought a " + potion.getCustomNameWithLevel() + " for " + potion.getHowMuchCoinsWorth() + " coins.");
                //TODO: consum logic
            }
            if (input == alchemistShopInventory.size()) {
                return false;
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
    }

     */

}
