package src.character.player;

import src.character.Character;
import src.character.npc.NPC;
import src.character.Ability;
import src.item.Inventory;

public abstract class Player extends Character implements IPlayer {
    public Inventory inventory;
    private int experience;
    private char gender;
    private Ability[] abilities = new Ability[3];

    public Player(String name, int healthPoints, char gender, int experience, Inventory inventory) {
        super(name, healthPoints);
        this.gender = gender;
        this.inventory = inventory;
        this.experience = experience;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void attack(NPC Npc, int index) {
        Npc.setHealthPoints(Npc.getHealthPoints() - getInventory().getEquippedWeapon().getAbility(index).getBaseDamage());
    }
}
