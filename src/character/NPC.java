package src.character;

import src.character.Character;
import src.item.Armor;
import src.item.armor.*;
import src.item.Weapon;
import src.item.weapon.*;

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
        if (Math.random() < 0.5) {
            if (player.getType().equals("Melee"))
                return new Sword(args[0], args[1]);
            else
                return new EnchantedBook(args[0], args[1]);
        } else {
            if (player.getType().equals("Melee"))
                return new Bow(args[0], args[1]);
            else
                return new Wand(args[0], args[1]);
        }
    }

    protected abstract int getDefenseForArmor(Player player);

    public Armor dropArmor(Player player) {
        int def = getDefenseForArmor(player);
        int randomType = (int) (Math.random() * 3);
        if (randomType == 1)
            return new Necklace(def);
        else if (randomType == 2)
            return new Shield(def);
        else
            return new Ring(def);
    }
}