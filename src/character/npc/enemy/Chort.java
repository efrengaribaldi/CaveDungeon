package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;

import java.util.concurrent.ThreadLocalRandom;

public class Chort extends Enemy {
    public Chort() {
        super("Chort", 50, 15);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(5, 10 + 1);
    }

    @Override
    public Weapon dropWeapon(Player player) {
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
        case 1:
            return (player.getType() == 'e') ? new Sword(21, 8) : new EnchantedBook(21, 8);
        case 2:
            return (player.getType() == 'e') ? new Bow(21, 8) : new Wand(21, 8);
        default:
            return null;
        }
    }
}