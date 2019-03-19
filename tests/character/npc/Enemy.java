package tests.character.npc;

import tests.character.NPC;

public class Enemy extends NPC {
    private int attackBaseDamage;

    public Enemy(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints);
        this.attackBaseDamage = attackBaseDamage;
    }

    public Enemy() {
        super("e", 10);
    }
}
