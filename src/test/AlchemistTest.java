package test;

import com.compScience.game.entities.Player;
import com.compScience.game.entities.npcs.Alchemist;
import com.compScience.game.utils.items.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlchemistTest {

    @Test
    void addPotionToPlayersInventoryIfPotionAlreadyExists() {
        Player player = new Player();
        player.createNewPlayerObject();


        Potion potion2 = new Potion("Potion of Healing", 10, 4, 1, player);

        int expectedValue = player.getPlayerInventory().getPotionInInventory().get(0).getPotionAmount() + 1;

        Alchemist alchemist = new Alchemist("Old Alchemist", player);
        alchemist.addPotionToPlayersInventory(player, potion2);

        int amount = player.getPlayerInventory().getPotionInInventory().get(0).getPotionAmount();

        assertEquals(expectedValue, amount);
    }

    @Test
    void setPlayersMoneyAfterPotionBought() {
        Player player = new Player();
        player.createNewPlayerObject();

        Potion potion = new Potion("Potion of Healing", 5, 1, 1, player);

        Alchemist alchemist = new Alchemist("Old Alchemist", player);

        double expectedValue = player.getMoneyCounter() - potion.getHowMuchCoinsWorth();
        alchemist.editPlayersMoneyAfterTransaction(player, potion);
        assertEquals(expectedValue, player.getMoneyCounter());
    }
}