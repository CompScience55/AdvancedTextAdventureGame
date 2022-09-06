package com.compScience.game.entities.npcs;

import com.compScience.game.entities.Player;

import java.util.Scanner;

public class Blacksmith extends NPC {

    Scanner scanner = new Scanner(System.in);

    public Blacksmith(String name, Player player) {
        super(name);
    }

    public void showBlacksmithInventory(Player player) {
        boolean isDone = true;
        while (isDone) {
            System.out.println("You meet a middle aged man with dirty clothes.");
            System.out.println("====================");
            System.out.println("You: Hello, who are you?");
            System.out.println("Blacksmith: Hello Stranger, I'm John the blacksmith. Do you need some stuff?");
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
                            //TODO: purchase
                            //isDone =
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
}
