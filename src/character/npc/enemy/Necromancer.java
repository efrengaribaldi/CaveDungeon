package src.character.npc.enemy;

import src.character.npc.Enemy;

public class Necromancer extends Enemy {
    public Necromancer() {
        super("Necromancer", 200, 55);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(15, 20 + 1);
    }
}
