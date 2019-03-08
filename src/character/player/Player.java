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

    public String printPlayerAbilities() {
         return inventory.getEquippedWeapon().printAbilities();
    }

    public String playerToString() {
        String res = "";
        res += "| Player Name: " + getName() + " | \n";
        res += "| Health Points: " + getHealthPoints() + " | \n";
        res += "| Inventory: | \n" + inventory.inventoryToString();
        res += "| Experience: " + experience + " | \n";
        res += "| Gender: " + gender + " | \n";
        res += printPlayerAbilities();
        return res;
    }
}
