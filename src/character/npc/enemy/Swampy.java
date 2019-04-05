package src.character.npc.enemy;

import src.character.npc.Enemy;

import java.util.concurrent.ThreadLocalRandom;

public class Swampy extends Enemy {
    public Swampy() {
        super("Swampy", 100, 30);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(8, 15 + 1);
    }
}
