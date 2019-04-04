package src.character.npc.enemy;

import src.character.npc.Enemy;

public class Skeleton extends Enemy {
    public Skeleton() {
        super("Skeleton", 30, 8);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(3, 7 + 1);
    }
}
