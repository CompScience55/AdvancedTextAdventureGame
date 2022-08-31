package com.compScience.game;

import com.compScience.game.entities.*;
import com.compScience.game.entities.forest.Bear;
import com.compScience.game.entities.forest.Spider;
import com.compScience.game.entities.forest.Wolf;
import com.compScience.game.entities.forest.Zombie;
import com.compScience.game.entities.mountains.Ghost;
import com.compScience.game.entities.mountains.Goblin;
import com.compScience.game.entities.mountains.Griffin;
import com.compScience.game.entities.mountains.Werewolf;
import com.compScience.game.entities.plains.Bandit;
import com.compScience.game.entities.plains.EarthGolem;
import com.compScience.game.entities.plains.Slime;
import com.compScience.game.entities.plains.Snake;
import com.compScience.game.utils.Inventory;
import com.compScience.game.utils.items.Potion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdventureGame {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();

    //Test Entity
    Entity e;

    //Util stuff
    Random r = new Random();

    //Map stuff
    private int mapDifficultySelection;
    private int mapZoneSelection;

    //Merchants
    private boolean alchemistFound = false;
    private boolean blacksmithFound = false;
    private boolean wizardFound = false;

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
        System.out.println("3: Rest");
        //Loading and saving of players savedata.
        System.out.println("4: Load Recent Player Data");
        System.out.println("5: Save & Exit");
        System.out.println("====================");
    }

    //Zone difficulty selection - Player can choose where he wants to go (difficulty)
    public void showZoneDifficultySelectionMenu() {
        System.out.println("Where do you want to go?");
        System.out.println("1: The Edge [LVL 1-4]");
        System.out.println("2: The Crossing [LVL 5-9]");
        System.out.println("3: The Deep [LVL 10-14]");
        System.out.println("====================");

        if (scanner.hasNextInt()) {
            int playerMenuInput = scanner.nextInt();

            if (playerMenuInput == 1 || playerMenuInput == 2 || playerMenuInput == 3 ) {
                switch (playerMenuInput) {
                    case 1: mapDifficultySelection = 1;
                    break;
                    case 2: mapDifficultySelection = 2;
                    break;
                    case 3: mapDifficultySelection = 3;
                    break;
                }
            } else {
                System.out.println("Use the options above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
    }

    //Zone selection - Player can choose where he wants to go
    public void showDifferentExploreZonesMenu() {
        System.out.println("Where do you want to go?");
        System.out.println("1: The Plains");
        System.out.println("2: The Forest");
        System.out.println("3: The Mountains");
        System.out.println("====================");

        if (scanner.hasNextInt()) {
            int playerMenuInput = scanner.nextInt();
            if (playerMenuInput == 1 || playerMenuInput == 2 || playerMenuInput == 3 ) {
            //Decides where the player goes and what difficulty he will receive
            switch (playerMenuInput) {
                case 1: mapZoneSelection = 1;
                break;
                case 2: mapZoneSelection = 2;
                break;
                case 3: mapZoneSelection = 3;
                break;
            }
            showZoneDifficultySelectionMenu();
            } else {
                System.out.println("Use the options above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
    }

    public boolean processPlayerWanderingLoopInput() {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            switch (attackMenuInput) {
                case 1: {
                    showDifferentExploreZonesMenu();
                    createNewRandomEntity(mapDifficultySelection, mapZoneSelection);
                    showAttackLoopMenu();
                    checkIfMerchantWasFound(mapZoneSelection, mapDifficultySelection);
                    System.out.println("Blacksmith:" + blacksmithFound);
                    System.out.println("Alchemist:" + alchemistFound);
                    System.out.println("wizard:" + wizardFound);
                    //TODO: Merchant
                    break;
                }
                case 2: {
                    //Inventory Menu Loop
                    boolean playerWantsToExitInventory = true;

                    while (playerWantsToExitInventory) {
                        showInventoryMenuOptions();
                        playerWantsToExitInventory = processPlayerInventoryMenuInput();
                    }
                }
                case 3: {
                    //Nap
                    processPlayerGoesResting(player);
                    break;
                }
                case 5: {
                    //Exit
                    return false;
                }
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return true;
    }

    //player resting
    public void processPlayerGoesResting(Player player) {
        System.out.println("You go into the nearest city for resting. This will refill your stats.");
        System.out.println("Staying a night costs 10 coins. Do you want to continue?");
        System.out.println("1: Yes");
        System.out.println("2: No");

        if (scanner.hasNextInt()) {
            int menuInput = scanner.nextInt();

            if (menuInput == 1) {
                if (player.getMoneyCounter() >= 10) {
                    player.setMoneyCounter(player.getMoneyCounter() - 10);
                    System.out.println("You stayed a night. Your stats have been refilled!");
                    player.setHealthPoints(player.getPlayerLevelHealthPoints());
                    player.setManaPoints(player.getPlayerLevelManaPoints());
                } else {
                    System.out.println("You don't have enough coins to rest.");
                }
            }
        } else {
            System.out.println("Use digits like '1'!");
        }

    }

    //merchant found
    public void checkIfMerchantWasFound(int mapZoneSelection, int mapDifficultySelection) {
        int difficultyMerchantChanceBoundary = 0;

        if (mapDifficultySelection == 1) {
            difficultyMerchantChanceBoundary = 15;
        }
        if (mapDifficultySelection == 3) {
            difficultyMerchantChanceBoundary = 20;
        }
        if (mapDifficultySelection == 3) {
            difficultyMerchantChanceBoundary = 25;
        }


        switch (mapZoneSelection) {
            case 1: {
                int randomIndex = r.nextInt(100)+1;
                if (randomIndex <= difficultyMerchantChanceBoundary) {
                    alchemistFound = true;
                }
                break;
            }
            case 2: {
                int randomIndex = r.nextInt(100)+1;
                if (randomIndex <= difficultyMerchantChanceBoundary) {
                    wizardFound = true;
                }
                break;
            }
            case 3: {
                int randomIndex = r.nextInt(100)+1;
                if (randomIndex <= difficultyMerchantChanceBoundary) {
                    blacksmithFound = true;
                }
                break;
            }
        }
    }

    //TODO: Edit Shop
    /*
    //merchant shop
    public void openMerchantShop() {
        if (alchemistFound) {
            ArrayList<Potion> alchemistInventory = new ArrayList<>();
            alchemistInventory.add(new Potion("Potion of Healing", 5, 3, 1, player));
            alchemistInventory.add(new Potion("Mana Potion", 5, 3, 1, player));
            System.out.println("====================");
            System.out.println("Alchemist: Hello Traveler! Are you interested in some goods?");
            System.out.println("====================");
            System.out.println("Alchemist's Inventory:");
            for (int i = 0; i < alchemistInventory.size(); i++) {
                System.out.println(i+1 + ": " + alchemistInventory.get(i).getName() + " | Price: " + alchemistInventory.get(i).getHowMuchCoinsWorth() + " Coins.");
            }
            System.out.println("====================");

            if (scanner.hasNextInt()) {
                int index = scanner.nextInt();
                //Buy item
                buyItemFromAlchemist(player.getPlayerInventory(), alchemistInventory, index);
            } else {
                System.out.println("Use digits like '1'!");
            }
        }
    }
    //merchant buy logic
    public void buyItemFromAlchemist(Inventory playerInventory, ArrayList<Potion> alchemistInventory, int itemIndex) {
        System.out.println("Y");
    }
    
     */

    //method for creating random enemies
    public void createNewRandomEntity(int mapDifficultySelection, int mapZoneSelection) {

        int newEntityIndex = r.nextInt(4)+1;

        //Entity with Zone and Level
        switch (mapZoneSelection) {
            case 1: {
                //Plains
                switch (mapDifficultySelection) {
                    //The Edge
                    case 1: {
                        int randomEntityLevel = r.nextInt(4)+1;
                        //Entity type selection
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Slime(randomEntityLevel, 0.2* randomEntityLevel);
                                break;
                            }
                            case 2: {
                                e = new Bandit(randomEntityLevel, 0.6 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Snake(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new EarthGolem(randomEntityLevel, 1.2 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Crossing
                    case 2: {
                        int randomEntityLevel = r.nextInt(6)+4;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Slime(randomEntityLevel, 0.2* randomEntityLevel);
                                break;
                            }
                            case 2: {
                                e = new Bandit(randomEntityLevel, 0.6 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Snake(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new EarthGolem(randomEntityLevel, 1.2 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Deep
                    case 3: {
                        int randomEntityLevel = r.nextInt(6)+9;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Slime(randomEntityLevel, 0.2* randomEntityLevel);
                                break;
                            }
                            case 2: {
                                e = new Bandit(randomEntityLevel, 0.6 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Snake(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new EarthGolem(randomEntityLevel, 1.2 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (mapDifficultySelection) {
                    //The Edge
                    case 1: {
                        int randomEntityLevel = r.nextInt(4)+1;
                        //Entity type selection
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Wolf(randomEntityLevel, randomEntityLevel * 0.6);
                                break;
                            }
                            case 2: {
                                e = new Zombie(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Spider(randomEntityLevel, 0.25 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Bear(randomEntityLevel, 0.85 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Crossing
                    case 2: {
                        int randomEntityLevel = r.nextInt(6)+4;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Wolf(randomEntityLevel, randomEntityLevel * 0.6);
                                break;
                            }
                            case 2: {
                                e = new Zombie(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Spider(randomEntityLevel, 0.25 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Bear(randomEntityLevel, 0.85 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Deep
                    case 3: {
                        int randomEntityLevel = r.nextInt(6)+9;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Wolf(randomEntityLevel, randomEntityLevel * 0.6);
                                break;
                            }
                            case 2: {
                                e = new Zombie(randomEntityLevel, 0.75 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Spider(randomEntityLevel, 0.25 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Bear(randomEntityLevel, 0.85 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (mapDifficultySelection) {
                    //The Edge
                    case 1: {
                        int randomEntityLevel = r.nextInt(4)+1;
                        //Entity type selection
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Goblin(randomEntityLevel, randomEntityLevel * 0.3);
                                break;
                            }
                            case 2: {
                                e = new Griffin(randomEntityLevel, 1.8 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Ghost(randomEntityLevel, 0.7 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Werewolf(randomEntityLevel, 0.9 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Crossing
                    case 2: {
                        int randomEntityLevel = r.nextInt(6)+4;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Goblin(randomEntityLevel, randomEntityLevel * 0.3);
                                break;
                            }
                            case 2: {
                                e = new Griffin(randomEntityLevel, 1.8 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Ghost(randomEntityLevel, 0.7 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Werewolf(randomEntityLevel, 0.9 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                    //Deep
                    case 3: {
                        int randomEntityLevel = r.nextInt(6)+9;
                        switch (newEntityIndex) {
                            case 1: {
                                e = new Goblin(randomEntityLevel, randomEntityLevel * 0.3);
                                break;
                            }
                            case 2: {
                                e = new Griffin(randomEntityLevel, 1.8 * randomEntityLevel);
                                break;
                            }
                            case 3:
                                e = new Ghost(randomEntityLevel, 0.7 * randomEntityLevel);
                                break;
                            case 4: {
                                e = new Werewolf(randomEntityLevel, 0.9 * randomEntityLevel);
                            }
                        }
                        break;
                    }
                }
                break;
            }
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
        System.out.println("Your HP: " + player.getHealthPoints() + " |  " + e.getEntityName() + " HP: " + e.getHealthPoints());
        System.out.println("Your MP: " + player.getManaPoints());
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Attack");
        System.out.println("2: Show Inventory");
        System.out.println("====================");
    }

    public int showDifferentAttackingOptionsForPlayer() {
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Melee Attack");
        System.out.println("2: Magic");
        System.out.println("====================");

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            if (input == 1 || input == 2) {
                return input;
            } else {
                System.out.println("Use the options from above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return 999;
    }

    public boolean processPlayerMenuInput() {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            if (attackMenuInput == 1) {
                int attackOptionsIndex = showDifferentAttackingOptionsForPlayer();
                return player.playerAttacksOtherEntity(e, attackOptionsIndex);
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
             if (isEnemyAlive)
              isPlayerAlive = e.attackPlayer(player);
        }
    }
}
