package src.item;

public class Inventory {
    private Armor[] armor;
    private Potion[] potions;
    private Weapon[] weapons;

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

    public Weapon selectedWeapon(int index) {
        return weapons[index];
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

    public void setPotion(Potion potion, int index) {
        potions[index] = potion;
    }

    public void removePotion(int index) {
        potions[index] = null;
    }

    public Armor getArmor(int index) {
        return armor[index];
    }

    public void setArmor(Armor armor, int index) {
        this.armor[index] = armor;
    }

    public void removeArmor(int index) {
        armor[index] = null;
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
            res += (potions[i] != null) ? potions[i].getName() + " | Recovery points: " + potions[i].getRecoveryPoints()
                    : "*Empty*";
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
