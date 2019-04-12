package src.character;

import src.character.Character;
import src.item.Weapon;
import src.item.weapon.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class NPC extends Character {
    private double baseDamage;
    private double baseDefense;

    public NPC(String name, int healthPoints, double baseDamage, double baseDefense) {
        super(name, healthPoints);
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
    }

    public int attack(Player player) {
        double damage = (baseDamage * 0.6) + baseDamage * Math.random() * 0.6;
        double defense = player.getDefense();
        int totalAttack = (int) (damage * damage / (damage + defense));
        player.setHealthPoints(player.getHealthPoints() - totalAttack);
        return totalAttack;
    }

    public double getDefense() {
        return baseDefense;
    }

    public abstract int getExperience();

    protected abstract int[] getArgsForWeapon(Player player);

    public Weapon dropWeapon(Player player) {
        int[] args = getArgsForWeapon(player);
        switch (ThreadLocalRandom.current().nextInt(1, 2)) {
        case 1:
            return (player.getType().equals("Melee")) ? new Sword(args[0], args[1])
                    : new EnchantedBook(args[0], args[1]);
        case 2:
            return (player.getType().equals("Melee")) ? new Bow(args[0], args[1]) : new Wand(args[0], args[1]);
        default:
            return null;
        }
    }
}