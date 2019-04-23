package src.character.npc;

import src.character.NPC;

public abstract class Enemy extends NPC {
    public Enemy(String name, int healthPoints, int baseDamage, int baseDefense) {
        super(name, healthPoints, baseDamage, baseDefense);
    }

    @Override
    public String getParent() {
        return "Enemy";
    }

    public abstract int getExperience();
}