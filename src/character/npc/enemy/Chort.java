package src.character.npc.enemy;

import src.character.npc.Enemy;
import java.util.concurrent.ThreadLocalRandom;

public class Chort extends Enemy {
    public Chort() {
        super("Chort", 50, 15);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(5, 10 + 1);
    }
}
