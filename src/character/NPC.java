package src.character;

import src.character.Character;

public abstract class NPC extends Character {
    private int attackBaseDamage;

    public NPC(String name, int healthPoints, int attackBaseDamage) {
        super(name, healthPoints);
        this.attackBaseDamage = attackBaseDamage;
    }

    public int attack(Player player) {
        double damage = (attackBaseDamage * 0.6) + attackBaseDamage * Math.random() * 0.6;
        double defense = player.getDefense();
        int totalAttack = (int) (damage * damage / (damage + defense));
        player.setHealthPoints(player.getHealthPoints() - totalAttack);
        return totalAttack;
    }

    public abstract double getDefense();
}