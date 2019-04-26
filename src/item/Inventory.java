package src.item;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int indexEquippedWeapon;
    private Armor[] armor;
    private Potion[] potions;
    private Weapon[] weapons;

    public Inventory() {
        armor = new Armor[3];
        potions = new Potion[3];
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

    public Weapon getWeapon(int index) {
        return weapons[index];
    }

    public void removeWeapon(int index) {
        weapons[index] = null;
    }

    public Potion getPotion(int index) {
        return potions[index];
    }

    public void removePotion(int index) {
        potions[index] = null;
    }

    public Armor getArmor(int index) {
        return armor[index];
    }

    public void removeArmor(int index) {
        armor[index] = null;
    }

    public void equipWeapon(int index) {
        indexEquippedWeapon = index;
    }

    public void switchEquippedWeapons() {
        indexEquippedWeapon = (indexEquippedWeapon == 0) ? 1 : 0;
    }

    public Weapon getEquippedWeapon() {
        return weapons[indexEquippedWeapon];
    }

    public int getIndexEquippedWeapon() {
        return indexEquippedWeapon;
    }

    public String printWeapons() {
        String res = "Weapons:\n";
        for (int i = 0; i < weapons.length; ++i) {
            res += "i[" + i + "]: ";
            if (weapons[i] != null)
                res += weapons[i].getName() + " (" + weapons[i].getType() + ")";
            else
                res += "*Empty*";
            res += "\n";
        }
        return res;
    }

    public String printPotions() {
        String res = "Potions:\n";
        for (int i = 0; i < potions.length; ++i) {
            res += "i[" + i + "]: ";
            res += (potions[i] != null) ? potions[i].getName() : "*Empty*";
            res += "\n";
        }
        return res;
    }

    public String printArmor() {
        String res = "";
        res += "Armor:\n";
        int i;
        for (i = 0; i < armor.length; ++i) {
            res += "i[" + i + "]: ";
            res += (armor[i] != null) ? armor[i].getName() : "*Empty*";
            res += "\n";
        }
        return res;
    }

    public String inventoryToString() {
        return printArmor() + "\n" + printPotions() + "\n" + printWeapons();
    }
}
