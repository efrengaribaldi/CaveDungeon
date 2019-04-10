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
        return baseDefense * Math.pow(1.03, level);
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

    public void attack(NPC npc, int index) {
        // Update enemy healthpoints
        npc.setHealthPoints((int) (npc.getHealthPoints()
                - (getInventory().getEquippedWeapon().getAbility(index).getBaseDamage() * getAttack())));
        // Update player's stamina
        stamina -= getInventory().getEquippedWeapon().getAbility(index).getStaminaCost();
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
            if (inventory.getWeaponByIndex(index) == null) {
                inventory.addItemToInventory(newWeapon, index);
            } else {
                System.out.println("You already have a weapon in this position. Do you want to remove it (Y / N)?");
                if (sc.next().charAt(0) == 'Y') {
                    inventory.removeWeapon(index);
                    inventory.addItemToInventory(newWeapon, index);
                    inventory.equipWeapon(index);
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
