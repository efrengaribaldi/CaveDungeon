package src.item;

import src.item.*;
import src.item.misc.*;

public class Inventory {
    public Armor[] armor;
    public Potion[] potions;
    public Weapon[] weapons;

    public Inventory() {
        armor = new Armor[4];
        potions = new Potion[4];
        weapons = new Weapon[2];
    }

    public void addItemToInventory(Armor armor, int index) {
        this.armor[index] = armor;
    }

    public void addItemToInventory(Potion potion, int index) {
        this.potions[index] = potion;
    }

    public void addItemToInventory(Weapon weapon, int index) {
        this.weapons[index] = weapon;
    }

    public void equipWeapon(int index) {
        weapons[0].setWeaponState((index == 0));
        weapons[1].setWeaponState((index == 1));
    }

    public String showEquippedWeapon() {
        return (weapons[0].getWeaponState()) ? weapons[0].getName() : weapons[1].getName();
    }

    public Weapon getEquippedWeapon() {
        return (weapons[0].getWeaponState()) ? weapons[0] : weapons[1];
    }

    public Potion getPotionIndex(int index) {
        return potions[index];
    }

    public void setPotionIndex(Potion potion, int index) {
        potions[index] = potion;
    }

    public void removePotionIndex(int index) {
        potions[index] = null;
    }

    public String printWeapons() {
        String res = "Weapons:\n";
        for (int i = 0; i < weapons.length; ++i) {
            res += "i[" + i + "]: ";
            res += (weapons[i] != null) ? weapons[i].getName() + " | EQUIP: " + weapons[i].getWeaponState() : "null";
            res += "\n";
        }
        res += "\n";
        return res;
    }

    public String printPotions() {
        String res = "Potions:\n";
        for (int i = 0; i < potions.length; ++i) {
            res += "i[" + i + "]: ";
            res += (potions[i] != null) ? potions[i].getName() + " | Recovery points: " + potions[i].getRecoveryPoints()
                    : "null";
            res += "\n";
        }
        res += "\n";
        return res;
    }

    public String inventoryToString() {
        String res = "";
        res += "Armor:\n";
        int i;
        for (i = 0; i < armor.length; ++i) {
            res += "i[" + i + "]: ";
            res += (armor[i] != null) ? armor[i].getName() : "null";
            res += "\n";
        }
        res += "\n";
        res += "Potions:\n";
        for (i = 0; i < potions.length; ++i) {
            res += "i[" + i + "]: ";
            res += (potions[i] != null) ? potions[i].getName() : "null";
            res += "\n";
        }
        res += "\n";
        res += "Weapons:\n";
        for (i = 0; i < weapons.length; ++i) {
            res += "i[" + i + "]: ";
            res += (weapons[i] != null) ? weapons[i].getName() : "null";
            res += "\n";
        }
        res += "\n";
        return res;
    }
}
