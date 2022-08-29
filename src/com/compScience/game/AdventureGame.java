package com.compScience.game;

import com.compScience.game.entities.Entity;
import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;

import java.util.Scanner;

public class AdventureGame {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();

    //Test Entity
    Entity e = new Entity("Slime", 6, 15, 2);

    public void startGame() {
        //Simple Attack Cycle
        player.createNewPlayerObject();
        showAttackLoopMenu();
    }

    //Item Use Loop
    public void showInventoryMenuOptions() {
        System.out.println("====================");
        System.out.println("1: Use an Item");
        System.out.println("2: Exit Menu");
    }

    public boolean processPlayerInventoryMenuInput() {
        if (scanner.hasNextInt()) {
            int inventoryMenuInput = scanner.nextInt();

            if (inventoryMenuInput == 1) {
                //Item Consume Logic
                player.getPlayerInventory().showInventoryContent();
                System.out.println("====================");
                System.out.println("What inventory is your item in?");
                System.out.println("1: Item Inventory");
                System.out.println("2: Potion Inventory");
                System.out.println("====================");
                processItemConsumeInput();
            }
            if (inventoryMenuInput == 2) {
                return false;
            }

        } else {
            System.out.println("Use digits like '1'!");
            return true;
        }
        return true;
    }

    public void InventorySelection(int inventoryIndex) {

        switch (inventoryIndex) {
            case 1:
                //Item Use Logic
                break;
            case 2:
                //Item Selection
                System.out.println("====================");
                System.out.println("What Item do you want to use?");
                System.out.println("====================");
                int itemIndex = 999;
                if (scanner.hasNextInt()) {
                    itemIndex = scanner.nextInt()-1;
                } else {
                    System.out.println("Use digits like '1'!");
                }

                if (itemIndex != 999) {
                    Potion consumingPotion = player.getPlayerInventory().getPotionInInventory().get(itemIndex);
                    consumingPotion.consumePotion(consumingPotion);
                }
            break;
        }
    }

    public void processItemConsumeInput() {
        if (scanner.hasNextInt()) {
            int playerInventoryInput = scanner.nextInt();
            InventorySelection(playerInventoryInput);
        } else {
            System.out.println("Use digits like '1'!");
        }
    }


    public void showAttackMenuOptions() {
        System.out.println("====================");
        System.out.println("Your HP: " + player.getHealthPoints() + " |  Enemy HP: " + e.getHealthPoints());
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Attack");
        System.out.println("2: Show Inventory");
        System.out.println("====================");
    }

    public boolean processPlayerMenuInput() {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            if (attackMenuInput == 1) {
                return player.playerAttacksOtherEntity(e);
            }
            if (attackMenuInput == 2) {
                //Inventory Menu Loop
                boolean playerWantsToExitInventory = true;

                while (playerWantsToExitInventory) {
                    showInventoryMenuOptions();
                    playerWantsToExitInventory = processPlayerInventoryMenuInput();
                }
            }

        } else {
            System.out.println("Use digits like '1'!");
            return true;
        }
        return true;
    }

    //Attack cycle
    public void showAttackLoopMenu() {
        boolean isEnemyAlive = true;

        while (isEnemyAlive) {
            showAttackMenuOptions();
             isEnemyAlive = processPlayerMenuInput();
        }
    }
}
