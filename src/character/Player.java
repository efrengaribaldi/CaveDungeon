package src.character;

import src.character.Character;
import src.character.NPC;
import src.item.Inventory;

public abstract class Player extends Character {
    public Inventory inventory;
    private int experience;
    private int level;
    private char gender;
    private int healthPoints;
    private int limitHp;
    private int stamina;
    private int limitStamina;
    private double attack;

    public Player(String name, int healthPoints, char gender, Inventory inventory, double attack) {
        super(name, healthPoints);
        this.inventory = inventory;
        this.experience = 0;
        this.level = 1;
        this.gender = gender;
        this.healthPoints = 25;
        this.limitHp = healthPoints;
        this.stamina = 10;
        this.limitStamina = stamina;
        this.attack = attack;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
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

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getLimitStamina() {
        return limitStamina;
    }

    public void setLimitStamina(int limitStamina) {
        this.limitStamina = limitStamina;
    }

    public void attack(NPC Npc, int index) {

        Npc.setHealthPoints((int) (Npc.getHealthPoints()
                - (getInventory().getEquippedWeapon().getAbility(index).getBaseDamage() * getAttack())));

        stamina = stamina - getInventory().getEquippedWeapon().getAbility(index).getStaminaCost();
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
        this.experience += newExp;
        if (experience >= expRequiredForNextLevel) {
            // Advance level
            setLevel(level + 1);
            setLimitHp(limitHp + (int) (3 * Math.pow(1.07, level)));
            setHealthPoints(limitHp);
            setLimitStamina(limitStamina + (int) (4 * Math.pow(1.07, level)));
            setStamina(limitStamina);
            setAttack(attack * 1.07);
            setExperience(experience - expRequiredForNextLevel);
            System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println("Congratulations!!! You are now level " + level);
            System.out.println("Your attack damage has increased 10%!");
            System.out.println("Your HP limit has increased to: " + healthPoints);
            System.out.println("Your Stamina limit has increased to: " + stamina);
        } else {
            int expNeeded = expRequiredForNextLevel - experience;
            System.out.println("You need " + expNeeded + " experience to advance to next level.");
        }
        System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
    }
}