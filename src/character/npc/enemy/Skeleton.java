package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;
import src.character.player.*;

import java.util.concurrent.ThreadLocalRandom;

public class Skeleton extends Enemy {
    public Skeleton() {
        super("Skeleton", 30, 8);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(5, 7 + 1);
    }

    @Override
    public Weapon dropWeapon(Player player) {
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
            case 1:
                return (player instanceof Melee) ? new Sword(17, 6) : new MagicBook(17, 6);
            case 2:
                return (player instanceof Melee) ? new Bow(17, 6) : new MagicStaff(17, 6);
            default:
                return null;
        }
    }
}
