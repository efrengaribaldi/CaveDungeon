package src.character;

import src.character.Character;
import src.character.NPC;
import src.item.Inventory;

public abstract class Player extends Character {
    private static final long serialVersionUID = 1L;
    public Inventory inventory;
    private int experience;
    private int level;
    private char gender;
    private int baseLimitHp;
    private int stamina;
    private int baseLimitStamina;
    private double baseAttack;
    private double baseDefense;

    public Player(String name, int baseLimitHp, char gender, Inventory inventory, double attack, double defense) {
        super(name, 0);
        this.baseLimitHp = baseLimitHp;
        super.setHealthPoints(this.getLimitHp());
        this.inventory = inventory;
        this.experience = 0;
        this.level = 1;
        this.gender = gender;
        this.stamina = 10;
        this.baseLimitStamina = stamina;
        this.baseAttack = attack;
        this.baseDefense = defense;
        setHealthPoints(getLimitHp());
        setStamina(getLimitStamina());
    }

    public Player() {
    }

    @Override
    public String getParent() {
        return "Player";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public char getGender() {
        return gender;
    }

    public double getAttack() {
        return baseAttack * Math.pow(1.07, level);
    }

    public double getDefense() {
        double playersDef = baseDefense * Math.pow(1.25, level);
        double itemsDef = 0;
        for (int i = 0; i < 3; i++)
            if (inventory.getArmor(i) != null)
                itemsDef += inventory.getArmor(i).getBaseDefense();
        return playersDef + itemsDef;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getLimitHp() {
        return baseLimitHp + (int) (22 * Math.pow(1.12, level - 1));
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getLimitStamina() {
        return baseLimitStamina * 0 + (int) (14 * Math.pow(1.08, level - 1));
    }

    public int getExpRequiredForNextLevel() {
        return (int) (15 * Math.pow(1.07, level));
    }

    public int attack(NPC npc, int abilityIndex) {
        double damage = inventory.getEquippedWeapon().getAbility(abilityIndex).getBaseDamage() * getAttack();
        double defense = npc.getDefense();
        int totalAttack = (int) (damage * damage / (damage + defense));
        // Update enemy healthpoints
        npc.setHealthPoints(npc.getHealthPoints() - totalAttack);
        // Update player's stamina
        stamina -= inventory.getEquippedWeapon().getAbility(abilityIndex).getStaminaCost();
        return totalAttack;
    }

    public int attack(NPC npc) {
        double damage = 5 * getAttack();
        double defense = npc.getDefense();
        int totalAttack = (int) (damage * damage / (damage + defense));
        npc.setHealthPoints(npc.getHealthPoints() - totalAttack);
        return totalAttack;
    }

    public String printPlayerAbilities() {
        String res = "";
        for (int i = 0; i < 2; i++)
            res += "Weapon " + i + ":\n" + inventory.getWeapon(i).printAbilities();
        return res;
    }

    public String printWeaponAbilities() {
        return inventory.getEquippedWeapon().printAbilities();
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

    public String checkLevelUp(int newExp) {
        String res = "";
        int expRequiredForNextLevel = (int) (15 * Math.pow(1.07, level));
        if (newExp > 0)
            res = "You got " + newExp + " new experience.\n";
        experience += newExp;
        if (experience >= expRequiredForNextLevel) {
            // Advance level
            ++level;
            // Reset health and stamina
            setHealthPoints(getLimitHp());
            setStamina(getLimitStamina());
            experience -= expRequiredForNextLevel;
            res += "Congratulations!!! You are now level " + level + "\nYour attack damage has increased to: "
                    + getAttack() + "\nYour defense has increased to: " + getDefense()
                    + "\nYour HP limit has increased to: " + getHealthPoints()
                    + "\nYour Stamina limit has increased to: " + getLimitStamina();
            if (experience >= (int) (15 * Math.pow(1.07, level)))
                checkLevelUp(0);
            return res;
        } else {
            int expNeeded = expRequiredForNextLevel - experience;
            res += "You need " + expNeeded + " experience to advance to next level.";
            return res;
        }
    }

    public void usePotion(int index) {
        switch (inventory.getPotion(index).getType()) {
        case 'h':
            setHealthPoints(getLimitHp());
            break;
        case 's':
            setStamina(getLimitStamina());
            break;
        }
        inventory.removePotion(index);
    }
}
