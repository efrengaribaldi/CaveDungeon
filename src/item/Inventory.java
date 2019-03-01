package src.item;

import src.item.armor.*;
import src.item.chest.*;
import src.item.key.*;
import src.item.potion.*;
import src.item.weapon.*;

public class Inventory {
    public Item[][] items;

    public Inventory() {
        items = new Item[4][];
        items[0] = new Armor[4];
        items[1] = new Key[2];
        items[2] = new Potion[4];
        items[3] = new Weapon[2];
    }

    public void addItemToInventory(Item item, int index1, int index2) {
        this.items[index1][index2] = item;
    }

    public void removeItemFromInventory(int index1, int index2) {
        items[index1][index2] = null;
    }

    // public void equipWeapon(int index) {
        // items[3][0].setWeaponState((index == 0));
        // items[3][1].setWeaponState((index == 1));
    // }

    public String inventoryToString() {
        String res = "";
        for (int i = 0; i < items.length; ++i) {
            for (int j = 0; j < items[i].length; ++j) {
                res += "i[" + i + ", " + j + "]: ";
                res += (items[i][j] != null) ? items[i][j].getName() : "null";
                res += "\n";
            }
            res += "\n";
        }
        return res;
    }
}
