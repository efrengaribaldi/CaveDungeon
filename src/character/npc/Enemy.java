package src.character.npc;

import src.character.NPC;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Enemy extends NPC {
    public Enemy(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints, attackBaseDamage, 2);
    }

    public abstract int getExperience();

    protected abstract int[] getArgsForWeapon(Player player);

    public Weapon dropWeapon(Player player) {
        int[] args = getArgsForWeapon(player);
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
        case 1:
            return (player.getType() == 'e') ? new Sword(args[0], args[1]) : new EnchantedBook(args[0], args[1]);
        case 2:
            return (player.getType() == 'e') ? new Bow(args[0], args[1]) : new Wand(args[0], args[1]);
        default:
            return null;
        }
    }
    // public abstract Potion dropPotion(Player player);
}