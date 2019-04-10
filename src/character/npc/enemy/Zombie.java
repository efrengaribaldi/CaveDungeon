package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;
import src.item.Weapon;
import src.item.weapon.*;

import java.util.concurrent.ThreadLocalRandom;

public class Zombie extends Enemy {
    public Zombie() {
        super("Zombie", 20, 5);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(3, 4 + 1);
    }

    @Override
    public Weapon dropWeapon(Player player) {
        int bStamina = ThreadLocalRandom.current().nextInt(3, 7);
        int bAttack = ThreadLocalRandom.current().nextInt(12, 18);
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
        case 1:
            return (player.getType() == 'e') ? new Sword(bAttack, bStamina) : new EnchantedBook(bAttack, bStamina);
        case 2:
            return (player.getType() == 'e') ? new Bow(bAttack, bStamina) : new Wand(bAttack, bStamina);
        default:
            return null;
        }
    }
}