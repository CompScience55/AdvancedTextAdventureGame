package com.compScience.game;

import com.compScience.game.entities.Entity;
import com.compScience.game.entities.Player;

import java.util.Scanner;

public class AdventureGame {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();

    //Test Entity
    Entity e = new Entity("Slime", 6, 15, 2);

    public void startGame() {
        //Simple Attack Cycle
        player.createNewPlayerObject();
        player.showAllPlayerStats();
        e.showAllEntityStats();
        showAttackLoopMenu();
        player.showAllPlayerStats();
        e.showAllEntityStats();
    }


    public void showAttackMenuOptions() {
        System.out.println("====================");
        System.out.println("What do you want to do?");
        System.out.println("1: Attack");
        System.out.println("====================");
    }

    public boolean processPlayerMenuInput() {
        if (scanner.hasNextInt()) {
            int attackMenuInput = scanner.nextInt();

            if (attackMenuInput == 1) {
                return player.playerAttacksOtherEntity(e);
            }
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
