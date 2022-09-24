package com.compScience.game.entities;

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

import java.util.Random;

public class EntitySpawner {

    Random r = new Random();

    public Entity createNewRandomEntity(int mapDifficultySelection, int mapZoneSelection) {
        int bossSelectionIndex = r.nextInt(100)+1;

        if (bossSelectionIndex <= 5) {
            return new BossEntity("Eltrox", 100, 10, 1, "Sword Slash", "Kick", "Sword Tornado");
        } else {

            int newEntityIndex = r.nextInt(4) + 1;
            //Entity with Zone and Level
            switch (mapZoneSelection) {
                case 1: {
                    //Plains
                    return doEntityDifficultySelectionForPlains(newEntityIndex, mapDifficultySelection);
                }
                case 2: {
                    return doEntityDifficultySelectionForForest(newEntityIndex, mapDifficultySelection);
                }
                default: {
                    return doEntityDifficultySelectionForMountains(newEntityIndex, mapDifficultySelection);
                }
            }
        }

    }
    //Mountains
    private Entity doEntityDifficultySelectionForMountains(int newEntityIndex, int mapDifficultySelection) {
        switch (mapDifficultySelection) {
            //The Edge
            case 1: {
                int randomEntityLevel = r.nextInt(4)+1;
                //Entity type selection
                return chooseRandomEntityForMountains(newEntityIndex, randomEntityLevel);
            }
            //Crossing
            case 2: {
                int randomEntityLevel = r.nextInt(6)+4;
                return chooseRandomEntityForMountains(newEntityIndex, randomEntityLevel);
            }
            //Deep
           default: {
                int randomEntityLevel = r.nextInt(6)+9;
                return chooseRandomEntityForMountains(newEntityIndex, randomEntityLevel);
           }
        }
    }
    private Entity chooseRandomEntityForMountains(int newEntityIndex, int entityLevel) {
        switch (newEntityIndex) {
            case 1: {
                return new Goblin(entityLevel, entityLevel * 0.3);
            }
            case 2: {
                return new Griffin(entityLevel, 1.8 * entityLevel);
            }
            case 3:
                return new Ghost(entityLevel, 0.7 * entityLevel);
            default: {
                return new Werewolf(entityLevel, 0.9 * newEntityIndex);
            }
        }
    }
    //Forest
    private Entity doEntityDifficultySelectionForForest(int newEntityIndex, int mapDifficultySelection) {
        switch (mapDifficultySelection) {
            //The Edge
            case 1: {
                int randomEntityLevel = r.nextInt(4)+1;
                //Entity type selection
                return chooseRandomEntityForForest(newEntityIndex, randomEntityLevel);
            }
            //Crossing
            case 2: {
                int randomEntityLevel = r.nextInt(6)+4;
                return chooseRandomEntityForForest(newEntityIndex, randomEntityLevel);
            }
            //Deep
            default: {
                int randomEntityLevel = r.nextInt(6)+9;
                return chooseRandomEntityForForest(newEntityIndex, randomEntityLevel);
            }
        }
    }
    private Entity chooseRandomEntityForForest(int newEntityIndex, int entityLevel) {
        switch (newEntityIndex) {
            case 1: {
                return new Wolf(entityLevel, entityLevel * 0.6);
            }
            case 2: {
                return new Zombie(entityLevel, 0.75 * entityLevel);
            }
            case 3:
                return new Spider(entityLevel, 0.25 * entityLevel);
            default: {
                return new Bear(entityLevel, 0.85 * entityLevel);
            }
        }
    }
    //Plains
    private Entity doEntityDifficultySelectionForPlains(int entityIndex, int mapDifficultySelection) {
        switch (mapDifficultySelection) {
            //The Edge
            case 1: {
                int randomEntityLevel = r.nextInt(4)+1;
                //Entity type selection
                return chooseRandomEntityForPlains(entityIndex, randomEntityLevel);
            }
            //Crossing
            case 2: {
                int randomEntityLevel = r.nextInt(6)+4;
                return chooseRandomEntityForPlains(entityIndex, randomEntityLevel);
            }
            //Deep
            default: {
                int randomEntityLevel = r.nextInt(6)+9;
                return chooseRandomEntityForPlains(entityIndex, randomEntityLevel);
            }
        }
    }
    private Entity chooseRandomEntityForPlains(int newEntityIndex, int entityLevel) {
        switch (newEntityIndex) {
            case 1: {
                return new Slime(entityLevel, 0.2* entityLevel);
            }
            case 2: {
                return new Bandit(entityLevel, 0.6 * entityLevel);
            }
            case 3:
                return new Snake(entityLevel, 0.75 * entityLevel);
            default: {
                return new EarthGolem(entityLevel, 1.2 * entityLevel);
            }
        }
    }
}
