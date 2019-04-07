package src.character.npc;

import src.character.NPC;
import src.character.Player;
import src.item.Weapon;

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
                - (int) (ThreadLocalRandom.current().nextDouble((attackBaseDamage * 0.6), attackBaseDamage + 1) * (2.0 - player.getDefense())));
    }

    public abstract int getExperience();
    public abstract Weapon dropWeapon(Player player);
    //public abstract Potion dropPotion(Player player);
}
