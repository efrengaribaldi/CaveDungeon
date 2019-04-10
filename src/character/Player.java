package src.character;

import src.character.Character;
import src.character.NPC;
import src.item.Inventory;
import src.item.Weapon;

import java.util.Scanner;

public abstract class Player extends Character {
    Scanner sc = new Scanner(System.in);
    public Inventory inventory;
    private int experience;
    private int level;
    private char gender;
    private int baseLimitHp;
    private int stamina;
    private int baseLimitStamina;
    private double baseAttack;
    private double baseDefense;

    public Player(String name, int healthPoints, char gender, Inventory inventory, double attack, double defense) {
        super(name, healthPoints);
        this.inventory = inventory;
        this.experience = 0;
        this.level = 1;
        this.gender = gender;
        this.baseLimitHp = healthPoints;
        this.stamina = 10;
        this.baseLimitStamina = stamina;
        this.baseAttack = attack;
        this.baseDefense = defense;
    }

    public abstract char getType();

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public double getAttack() {
        return baseAttack * Math.pow(1.07, level);
    }

    public double getDefense() {
        double playersDef = baseDefense * Math.pow(1.03, level);
        double itemsDef = 0;
        for (int i = 0; i < 4; i++)
            if (inventory.getArmor(i) != null)
                itemsDef += inventory.getArmor(i).getBaseDefense();
        return playersDef + itemsDef;
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
        return baseLimitHp + (int) (4 * Math.pow(1.07, level));
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getLimitStamina() {
        return baseLimitStamina + (int) (3 * Math.pow(1.07, level));
    }

    public int attack(NPC npc, int weaponIndex, int abilityIndex) {
        double damage = inventory.getWeapon(weaponIndex).getAbility(abilityIndex).getBaseDamage() * getAttack();
        double defense = npc.getDefense();
        int totalAttack = (int) (damage * damage / (damage + defense));
        // Update enemy healthpoints
        npc.setHealthPoints((int) (npc.getHealthPoints() - totalAttack));
        // Update player's stamina
        stamina -= inventory.getWeapon(weaponIndex).getAbility(abilityIndex).getStaminaCost();
        return totalAttack;
    }

    public String printPlayerAbilities() {
        String res = "";
        for (int i = 0; i < 2; i++)
            res += "Weapon " + i + ":\n" + inventory.getWeapon(i).printAbilities();
        return res;
    }

    public String playerToString() {
        String res = "";
        res += "\n| Player Name: " + getName() + " |";
        res += "\n| Health Points: " + getHealthPoints() + " |";
        res += "\n| Inventory: |\n\t" + inventory.inventoryToString().replaceAll("\n", "\n\t");
        res += "\n| Experience: " + experience + " |";
        res += "\n| Gender: " + gender + " |";
        res += "\n| Abilities: |\n\t" + printPlayerAbilities().replaceAll("\n", "\n\t");
        return res;
    }

    public void checkLevelUp(int newExp) {
        int expRequiredForNextLevel = (int) (15 * Math.pow(1.07, level));
        System.out.println("You got " + newExp + " new experience.");
        experience += newExp;
        if (experience >= expRequiredForNextLevel) {
            // Advance level
            setLevel(++level);
            // Reset health and stamina
            setHealthPoints(getLimitHp());
            setStamina(getLimitStamina());
            setExperience(experience - expRequiredForNextLevel);
            System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println("Congratulations!!! You are now level " + level);
            System.out.println("Your attack damage has increased to: " + getAttack());
            System.out.println("Your defense has increased to: " + getDefense());
            System.out.println("Your HP limit has increased to: " + getHealthPoints());
            System.out.println("Your Stamina limit has increased to: " + getLimitStamina());
        } else {
            int expNeeded = expRequiredForNextLevel - experience;
            System.out.println("You need " + expNeeded + " experience to advance to next level.");
        }
        System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
    }

    public void getNewWeapon(Weapon newWeapon, int index) {
        try {
            if (inventory.getWeapon(index) == null) {
                inventory.addItemToInventory(newWeapon, index);
            } else {
                System.out.println("You already have a weapon in this position. Do you want to remove it (Y / N)?");
                if (sc.next().charAt(0) == 'Y') {
                    inventory.removeWeapon(index);
                    inventory.addItemToInventory(newWeapon, index);
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Index weapon not found!");
        }
    }

    public void usePotion(int index) {
        switch (inventory.getPotion(index).getType()) {
        case 'h':
            setHealthPoints(getHealthPoints() + inventory.getPotion(index).getRecoveryPoints());
            if (getHealthPoints() > getLimitHp())
                setHealthPoints(getLimitHp());
            break;
        case 's':
            setStamina(getStamina() + inventory.getPotion(index).getRecoveryPoints());
            if (getStamina() > getLimitStamina())
                setStamina(getLimitStamina());
            break;
        default:
            break;
        }
        inventory.removePotion(index);
    }
}
