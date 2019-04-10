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

    public String printWeapons() {
        String res = "Weapons:\n";
        for (int i = 0; i < weapons.length; ++i) {
            res += "i[" + i + "]: ";
            res += (weapons[i] != null) ? weapons[i].getName() : "null";
            res += "\n";
        }
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
        return res;
    }

    public String printArmor() {
        String res = "";
        res += "Armor:\n";
        int i;
        for (i = 0; i < armor.length; ++i) {
            res += "i[" + i + "]: ";
            res += (armor[i] != null) ? armor[i].getName() : "null";
            res += "\n";
        }
        return res;
    }

    public String inventoryToString() {
        return printArmor() + "\n" + printPotions() + "\n" + printWeapons();
    }
}
