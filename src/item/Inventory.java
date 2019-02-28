package src.item;

import src.item.armor.*;
import src.item.chest.*;
import src.item.key.*;
import src.item.potion.*;
import src.item.weapon.*;

public class Inventory {
    public Item[][] items;

    public Inventory() {
        // 0 armor, 1 keys, 2 potions, 3 weapons
        int[] itemSizes = { 4, 2, 4, 2 };
        items = new Item[5][];
        for (int i = 0; i < itemSizes.length; i++)
            items[i] = new Item[itemSizes[i]];
    }

    public void addItemToInventory(Item item, int index1, int index2) {
        this.items[index1][index2] = item;
    }

    public void removeItemFromInventory(int index1, int index2) {
        items[index1][index2] = null;
    }

    // public void equipWeapon(int index) {
    // switch(index) {
    // case 0:
    // items[4][0].setWeaponState(true);
    // items[4][1].setWeaponState(false);
    // break;
    // case 1:
    // items[4][0].setWeaponState(false);
    // items[4][1].setWeaponState(true);
    // break;
    // default:
    // break;
    // }
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