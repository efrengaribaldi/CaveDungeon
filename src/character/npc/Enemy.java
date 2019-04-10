package src.character.npc;

import src.character.NPC;
import src.character.Player;
import src.item.Weapon;

public abstract class Enemy extends NPC {
    public Enemy(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints, attackBaseDamage);
    }

    public abstract int getExperience();

    public abstract Weapon dropWeapon(Player player);
    // public abstract Potion dropPotion(Player player);

    @Override
    public double getDefense() {
        return 2;
    }
}