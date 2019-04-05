package src.character;

import src.character.Character;
import src.character.NPC;
import src.character.Ability;
import src.item.Inventory;

public abstract class Player extends Character {
    public Inventory inventory;
    private int experience;
    private int level;
    private char gender;
    private int stamina;
    private int limitHp;
    private int limitStamina;
    private double attack;
    private Ability[] abilities = new Ability[3];

    public Player(String name, int healthPoints, char gender, Inventory inventory) {
        super(name, healthPoints);
        this.gender = gender;
        this.inventory = inventory;
        this.experience = 0;
        this.level = 1;
        this.stamina = 10;
        this.limitHp = 25;
        this.limitStamina = 10;
        this.attack = 1;
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

    public void checkLevelUp() {
        if (this.experience >= 15 * (Math.pow(1.1, this.level))) {
            setLevel(this.level + 1);

            setLimitHp((int) Math.round(getLimitHp() + 2 * (Math.pow(1.1, this.level))));
            setHealthPoints(getLimitHp());
            setLimitStamina((int) Math.round(getLimitStamina() + 4 * (Math.pow(1.1, this.level))));
            setStamina(getLimitStamina());
            setAttack(getAttack() + 0.10);
            setExperience(0);

            System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println("Congratulations!!! You are now level " + this.getLevel());
            System.out.println("Your attack damage has increased 10%!");
            System.out.println("Your HP limit has increased to: " + this.getHealthPoints());
            System.out.println("Your Stamina limit has increased to: " + this.getStamina());
            System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
        }
    }
}
