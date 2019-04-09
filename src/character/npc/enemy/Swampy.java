package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;
import src.character.player.*;

import java.util.concurrent.ThreadLocalRandom;

public class Swampy extends Enemy {
    public Swampy() {
        super("Swampy", 100, 30);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(10, 15 + 1);
    }

    @Override
    public Weapon dropWeapon(Player player) {
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
        case 1:
            return (player instanceof Melee) ? new Sword(28, 9) : new EnchantedBook(28, 9);
        case 2:
            return (player instanceof Melee) ? new Bow(28, 9) : new Wand(28, 9);
        default:
            return null;
        }
    }
}