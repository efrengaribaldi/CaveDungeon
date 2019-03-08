package src.character.npc.enemy;

import src.character.npc.NPC;
import src.character.player.*;

public abstract class Enemy extends NPC {
    private int attackBaseDamage;

    public Enemy(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints);
        this.attackBaseDamage = attackBaseDamage;
    }

    public void attack(Player player) {
        player.setHealthPoints(player.getHealthPoints() - this.attackBaseDamage);
    }
}
