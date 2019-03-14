package src.character.player;

import src.character.Character;
import src.character.npc.NPC;
import src.character.Ability;
import src.item.Inventory;

public abstract class Player extends Character implements IPlayer {
    public Inventory inventory;
    private int experience;
    private int level;
    private char gender;
    private int limitHp;
    private Ability[] abilities = new Ability[3];

    public Player(String name, int healthPoints, char gender, Inventory inventory) {
        super(name, healthPoints);
        this.gender = gender;
        this.inventory = inventory;
        this.experience = 1;
        this.level = 1;
        this.limitHp = 25;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLimitHp() {
        return limitHp;
    }

    public void setLimitHp(int limitHp) {
        this.limitHp = limitHp;
    }

    public void attack(NPC Npc, int index) {
        switch(level) {
            case 1:
                Npc.setHealthPoints(Npc.getHealthPoints() - getInventory().getEquippedWeapon().getAbility(index).getBaseDamage());
                break;
            case 2:
                Npc.setHealthPoints(Npc.getHealthPoints() - (int)(getInventory().getEquippedWeapon().getAbility(index).getBaseDamage() * 1.25));
                break;
            case 3:
                Npc.setHealthPoints(Npc.getHealthPoints() - (int)(getInventory().getEquippedWeapon().getAbility(index).getBaseDamage() * 1.75));
                break;
            case 4:
                Npc.setHealthPoints(Npc.getHealthPoints() - (int)(getInventory().getEquippedWeapon().getAbility(index).getBaseDamage() * 2.50));
                break;
            default:
                break;
        }

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
