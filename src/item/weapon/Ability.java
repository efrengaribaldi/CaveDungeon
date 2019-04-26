package src.item.weapon;

import java.io.Serializable;

public class Ability implements Serializable{
    private String name;
    private int baseDamage;
    private int staminaCost;

    public Ability(String name, int baseDamage, int staminaCost) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.staminaCost = staminaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getStaminaCost() {
        return staminaCost;
    }
}
