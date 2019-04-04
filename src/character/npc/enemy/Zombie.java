package src.character.npc.enemy;

import src.character.npc.Enemy;

public class Zombie extends Enemy {
    public Zombie() {
        super("Zombie", 20, 5);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(1, 4 + 1);
    }
}
