package test;

import com.compScience.game.entities.Player;
import com.compScience.game.utils.items.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void playerHealthAfterPlayerUsesPotionRight() {
        Player player = new Player();
        player.createNewPlayerObject();
        Potion potion = new Potion("Potion of Healing", 10, 1, 1, player);
        double expectedValueOfPlayerHealth = player.getHealthPoints() + potion.getRefillAmount();
        potion.consumePotion(potion,player);
        assertEquals(expectedValueOfPlayerHealth, player.getHealthPoints());
    }
}