package com.compScience.game;

import com.compScience.game.entities.*;
import com.compScience.game.entities.items.ItemDropper;
import com.compScience.game.entities.npcs.Alchemist;
import com.compScience.game.entities.npcs.Wizard;
import com.compScience.game.utils.items.Potion;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdventureGame {

    private Scanner scanner = new Scanner(System.in);
    private Player player = new Player();

    //Test Entity
    private Entity e;

    //Util stuff
    private Random r = new Random();

    private boolean isInFight = false;

    //Map stuff
    private int mapDifficultySelection;
    private int mapZoneSelection;

    private EntitySpawner entitySpawner;
    private ItemDropper dropper;

    //Merchants
    private boolean alchemistFound = false;
    private boolean blacksmithFound = false;
    private boolean wizardFound = false;

    //Main Loop
    public void startGame()  {
        entitySpawner = new EntitySpawner();
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
    private void showPlayerWanderingLoopOptions() {
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
    private void showZoneDifficultySelectionMenu() {
        System.out.println("Where do you want to go?");
        System.out.println("1: The Edge [LVL 1-4]");
        System.out.println("2: The Crossing [LVL 5-9]");
        System.out.println("3: The Deep [LVL 10-14]");
        System.out.println("====================");

        if (scanner.hasNextInt()) {
            int playerMenuInput = scanner.nextInt();

            if (playerMenuInput == 1 || playerMenuInput == 2 || playerMenuInput == 3 ) {
                handlePlayerMenuInputForMapDifficulty(playerMenuInput);
            } else {
                System.out.println("Use the options above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
    }

    private void handlePlayerMenuInputForMapDifficulty(int input) {
        switch (input) {
            case 1 -> mapDifficultySelection = 1;
            case 2 -> mapDifficultySelection = 2;
            case 3 -> mapDifficultySelection = 3;
        }
    }

    //Zone selection - Player can choose where he wants to go
    private void showDifferentExploreZonesMenu() {
        System.out.println("Where do you want to go?");
        System.out.println("1: The Plains");
        System.out.println("2: The Forest");
        System.out.println("3: The Mountains");
        System.out.println("====================");

        if (scanner.hasNextInt()) {
            int playerMenuInput = scanner.nextInt();
            if (playerMenuInput == 1 || playerMenuInput == 2 || playerMenuInput == 3 ) {
            //Decides where the player goes and what difficulty he will receive
            handlePlayerMenuInputForMapZone(playerMenuInput);
            } else {
                System.out.println("Use the options above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
    }

    private void handlePlayerMenuInputForMapZone(int input) {
        switch (input) {
            case 1 -> mapZoneSelection = 1;
            case 2 -> mapZoneSelection = 2;
            case 3 -> mapZoneSelection = 3;
        }
        showZoneDifficultySelectionMenu();
    }

    private boolean processPlayerWanderingLoopInput()  {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            switch (attackMenuInput) {
                case 1 -> {
                    handleAttackProcedure();
                    break;
                }
                case 2 -> {
                    //Inventory Menu Loop
                    boolean playerWantsToExitInventory = true;
                    while (playerWantsToExitInventory) {
                        showInventoryMenuOptions();
                        playerWantsToExitInventory = processPlayerInventoryMenuInput();
                    }
                    break;
                }
                case 3 -> {
                    //Nap
                    processPlayerGoesResting(player);
                    break;
                }
                case 4 -> {
                    readPlayerData();
                    break;
                }
                case 5 -> {
                    savePlayerData();
                    return false;
                }
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return true;
    }
    private void handleAttackProcedure() {
        isInFight = true;
        showDifferentExploreZonesMenu();
        e = entitySpawner.createNewRandomEntity(mapDifficultySelection, mapZoneSelection);
        int playerDeathIndex = showAttackLoopMenu();
        if (playerDeathIndex == 1) {
            checkIfMerchantWasFound(mapZoneSelection, mapDifficultySelection);
            openMerchantShop();
        }
    }

    //Get Desktop path
    private String getDesktopPath() {
        String path = "";
        try {
        path = System.getProperty("user.home") + "//Desktop";
        path.replace("\\", "//");
    }catch (Exception e){
        System.out.println("Exception caught ="+e.getMessage());
    }
        return path;
    }

    //Player Save Data
    public void savePlayerData() {

        String desktopPath = getDesktopPath();

        if (!desktopPath.equals("")) {

            try {
                FileOutputStream fileOutputStream = new FileOutputStream( desktopPath+ "//saveData.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Something went wrong while saving!");
        }
    }
    //Player Read Data
    private void readPlayerData() {

        String desktopPath = getDesktopPath();

        if (!desktopPath.equals("")) {

            try {
                FileInputStream fi = new FileInputStream(desktopPath + "//saveData.dat");
                ObjectInputStream oi = new ObjectInputStream(fi);

                player = (Player) oi.readObject();

                oi.close();
                fi.close();
                System.out.println("=========================================");
                System.out.println("Game Loaded!");
                System.out.println("=========================================");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        } else {
            System.out.println("Something went wrong while loading!");
        }
    }

    //player resting
    private void processPlayerGoesResting(Player player) {
        System.out.println("You go into the nearest city for resting. This will refill your stats.");
        System.out.println("Staying a night costs 10 coins. Do you want to continue?");
        System.out.println("1: Yes");
        System.out.println("2: No");

        if (scanner.hasNextInt()) {
            int menuInput = scanner.nextInt();

            if (menuInput == 1) {
                checkForEnoughCoinsForResting();
            }
        } else {
            System.out.println("Use digits like '1'!");
        }

    }
    private void checkForEnoughCoinsForResting() {
        if (player.getMoneyCounter() >= 10) {
            player.setMoneyCounter(player.getMoneyCounter() - 10);
            System.out.println("You stayed a night. Your stats have been refilled!");
            player.setHealthPoints(player.getPlayerLevelHealthPoints());
            player.setManaPoints(player.getPlayerLevelManaPoints());
        } else {
            System.out.println("You don't have enough coins to rest.");
        }
    }

    //merchant found
    private void checkIfMerchantWasFound(int mapZoneSelection, int mapDifficultySelection) {
        int difficultyMerchantChanceBoundary = setMerchantSpawnChanceBoundary(mapDifficultySelection);
        doZoneSpecificMerchantCheck(mapZoneSelection, difficultyMerchantChanceBoundary);
    }
    private int setMerchantSpawnChanceBoundary(int mapDifficultySelection) {
        if (mapDifficultySelection == 1) {
            return 10;
        }
        if (mapDifficultySelection == 2) {
            return 15; //TODO: Reset values
        } else {
            return 25;
        }
    }
    //Zone Selection
    private void doZoneSpecificMerchantCheck(int mapZoneIndex, int difficultyMerchantChanceBoundary) {
        switch (mapZoneSelection) {
            case 1 -> {
                generateRandomIndexForAlchemist(difficultyMerchantChanceBoundary);
                break;
            }
            case 2 -> {
                generateRandomIndexForWizard(difficultyMerchantChanceBoundary);
                break;
            }
            case 3 -> {
                generateRandomIndexForBlacksmith(difficultyMerchantChanceBoundary);
                break;
            }
        }
    }

    //merchant boolean methods
    private void generateRandomIndexForBlacksmith(int boundary) {
        int randomIndex = r.nextInt(100)+1;
        if (randomIndex <= boundary) {
            blacksmithFound = true;
        }
    }
    private void generateRandomIndexForWizard(int boundary) {
        int randomIndex = r.nextInt(100)+1;
        if (randomIndex <= boundary) {
            wizardFound = true;
        }
    }
    private void generateRandomIndexForAlchemist(int boundary) {
        int randomIndex = r.nextInt(100)+1;
        if (randomIndex <= boundary) {
            alchemistFound = true;
        }
    }

    //TODO: Edit shop
    //merchant shop
    private void openMerchantShop() {
        if (alchemistFound) {
            Alchemist alchemist = new Alchemist("Old Alchemist", player);
            alchemist.showAlchemistInventory(player);
            alchemistFound = false;
        }
        if (wizardFound) {
            Wizard wizard = new Wizard("Wise Wizard", player);
            wizard.showWizardInventory(player);
            wizardFound = false;
        }
    }
    //Item use loop
    private void showInventoryMenuOptions() {
        System.out.println("====================");
        System.out.println("1: Use an Item");
        System.out.println("2: Exit Menu");
    }

    private boolean processPlayerInventoryMenuInput() {
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

    private void InventorySelection(int inventoryIndex) {

        switch (inventoryIndex) {
            case 1:
                //Item Use Logic
                break;
            case 2:
                //Item Selection
                if (!player.getPlayerInventory().getPotionInInventory().isEmpty()) {
                    System.out.println("====================");
                    System.out.println("What Item do you want to use?");
                    System.out.println("====================");

                    int itemIndex = 9999;
                    itemIndex = checkForCorrectUserInputInItemSelection();
                    processCorrectUserInputForPotion(itemIndex);
                } else {
                    System.out.println("You don't have any potions in your inventory.");
                }
            break;
        }
    }
    private void processCorrectUserInputForPotion(int itemIndex) {
        if (itemIndex != 9999) {
            Potion consumingPotion = player.getPlayerInventory().getPotionInInventory().get(itemIndex);
            switch (consumingPotion.getPotionIdentifier()) {
                case 1 -> consumingPotion.consumePotion(consumingPotion, player);
                case 2 -> consumingPotion.consumePotionDealingDamage(e, consumingPotion, this);
            }


            if (consumingPotion.getPotionAmount() == 0) {
                player.getPlayerInventory().getPotionInInventory().remove(itemIndex);
            }
        }
    }
    private int checkForCorrectUserInputInItemSelection() {

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt() - 1;
            if (0 <= input && input <= player.getPlayerInventory().getPotionInInventory().size()) {
                return input;
            } else {
                System.out.println("Use the options above!");
            }
        } else {
            System.out.println("Use digits like '1'!");
        }
        return 9999;
    }

    private void processItemConsumeInput() {
        ArrayList<Integer> possibleChoices = new ArrayList<Integer>();
        possibleChoices.add(1);
        possibleChoices.add(2);
        int playerInventoryInput = checkForCorrectUserInputInMenus(possibleChoices);
        InventorySelection(playerInventoryInput);
    }

    //Attacking loop stuff
    private void showAttackMenuOptions() {
        System.out.println("====================");
        System.out.println("Your Level: " + player.getPlayerLevel() + " | Your Progress: (" + player.getCurrentXpProgressionCounter() + " / " + player.getXpBorder() + ")");
        System.out.println("Your HP: " + player.getHealthPoints() + " |  " + e.getEntityName() + " HP: " + e.getHealthPoints());
        System.out.println("Your MP: " + player.getManaPoints());
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Attack");
        System.out.println("2: Show Inventory");
        System.out.println("====================");
    }

    private int showDifferentAttackingOptionsForPlayer() {
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

    private boolean processPlayerMenuInput() {
        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            if (input == 1) {
                int attackOptionsIndex = showDifferentAttackingOptionsForPlayer();
                return player.playerAttacksOtherEntity(scanner,e, attackOptionsIndex, this);
            }
            if (input == 2) {
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
    private int showAttackLoopMenu() {
        boolean isEnemyAlive = true;
        boolean isPlayerAlive = true;

        while (isEnemyAlive && isPlayerAlive) {
            showAttackMenuOptions();
             isEnemyAlive = processPlayerMenuInput();
             if (isEnemyAlive)
              isPlayerAlive = e.attackPlayer(player, this);

             if (!isPlayerAlive) {
                 return 0;
             }
             if (!isEnemyAlive) {
                 int randomIndex = r.nextInt(100)+1;
                 if (randomIndex <= 3) {
                     double coinsFound = r.nextDouble() + r.nextInt(23) + 1;
                     player.setMoneyCounter(player.getMoneyCounter() + coinsFound);
                     System.out.println("You just found " + coinsFound + " coins in a bag from your enemy!");
                 }
                 dropper = new ItemDropper(e);
                 dropper.dropRandomItem(player);
             }
        }
        return 1;
    }

    private int checkForCorrectUserInputInMenus(ArrayList<Integer> possibleChoices) {
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

    public void setInFight(boolean inFight) {
        isInFight = inFight;
    }

    public boolean isInFight() {
        return isInFight;
    }

    //TODO: Edit Menu Input in every Menu via Method
}
