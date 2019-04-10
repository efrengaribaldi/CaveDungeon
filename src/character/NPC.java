package src.character;

import src.character.Character;

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
}