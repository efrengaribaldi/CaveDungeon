package src.character.npc.enemy;

import src.character.npc.NPC;
import src.character.player.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Enemy extends NPC {
    private int attackBaseDamage;

    public Enemy(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints);
        this.attackBaseDamage = attackBaseDamage;
    }

    public void attack(Player player) {
        // 70% baseAttackDamage
        player.setHealthPoints(player.getHealthPoints()
                - ThreadLocalRandom.current().nextInt((int) (this.attackBaseDamage * 0.7), this.attackBaseDamage + 1));
    }
}
