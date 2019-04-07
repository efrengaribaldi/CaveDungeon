package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;
import src.character.player.*;

import java.util.concurrent.ThreadLocalRandom;

public class Necromancer extends Enemy {
    public Necromancer() {
        super("Necromancer", 200, 55);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(15, 20 + 1);
    }

    @Override
    public Weapon dropWeapon(Player player) {
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
            case 1:
                return (player instanceof Melee) ? new Sword(39, 10) : new MagicBook(39, 10);
            case 2:
                return (player instanceof Melee) ? new Bow(39, 10) : new MagicStaff(39, 10);
            default:
                return null;
        }
    }
}
