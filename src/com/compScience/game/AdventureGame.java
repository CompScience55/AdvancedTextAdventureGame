package com.compScience.game;

import com.compScience.game.entities.*;
import com.compScience.game.utils.items.Potion;

import java.util.Random;
import java.util.Scanner;

public class AdventureGame {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();

    //Test Entity
    Entity e;

    //Util stuff
    Random r = new Random();

    //Main Loop
    public void startGame() {
        //Over Attack Encounter Cycle

        //Simple Attack Cycle
        player.createNewPlayerObject();
        showWanderingLoopMenu();

    }
    //Wandering Loop
    public void showWanderingLoopMenu() {
        boolean playerNotWantsToExit = true;

        while (playerNotWantsToExit) {
            showPlayerWanderingLoopOptions();
            playerNotWantsToExit = processPlayerWanderingLoopInput();
        }
    }

    //player wandering loop
    public void showPlayerWanderingLoopOptions() {
        System.out.println("====================");
        System.out.println("Your HP: " + player.getHealthPoints() + " | Money: " + player.getMoneyCounter());
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Explore");
        System.out.println("2: Show Inventory");
        //Loading and saving of players savedata.
        System.out.println("3: Load Recent Player Data");
        System.out.println("4: Save & Exit");
        System.out.println("====================");
    }

    public boolean processPlayerWanderingLoopInput() {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            if (attackMenuInput == 1) {
                createNewRandomEntity();
                showAttackLoopMenu();
            }
            if (attackMenuInput == 2) {
                //Inventory Menu Loop
                boolean playerWantsToExitInventory = true;

                while (playerWantsToExitInventory) {
                    showInventoryMenuOptions();
                    playerWantsToExitInventory = processPlayerInventoryMenuInput();
                }
            }
            if (attackMenuInput == 4) {
                //exit
                return false;
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return true;
    }
    //method for creating random enemies
    public void createNewRandomEntity() {

        int newEntityIndex = r.nextInt(4)+1;

        switch (newEntityIndex) {
            case 1: e = new Slime(player.getPlayerLevel()+2, 3);
                    break;
            case 2: e = new Wolf(player.getPlayerLevel() + 3, 4);
                    break;
            case 3: e = new Zombie(player.getPlayerLevel() + 1, 7);
                    break;
            case 4: e = new Spider(player.getPlayerLevel(), 2.5);
                    break;
            //More cases
        }

    }

    //Item use loop
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


    //Attacking loop stuff
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
        boolean isPlayerAlive = true;

        while (isEnemyAlive && isPlayerAlive) {
            showAttackMenuOptions();
             isEnemyAlive = processPlayerMenuInput();
             isPlayerAlive = e.attackPlayer(player);
        }
    }
}
