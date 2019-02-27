package src.item;

import src.item.armor.*;
import src.item.chest.*;
import src.item.key.*;
import src.item.potion.*;
import src.item.weapon.*;

public class Inventory {
    public Item[][] items;

    public Inventory() {
      items = new Item[5][]; // Five classes for Items
      items[0] = new Armor[4];
      items[1] = new Chest[3];
      items[2] = new Key[2];
      items[3] = new Potion[4];
      items[4] = new Weapon[2];

    }

    public void addItemToInventory(Item item, int index1, int index2) {
      this.items[index1][index2] = item;
    }

    public void removeItemFromInventory(int index1, int index2) {
      items[index1][index2] = null;
    }

    public String inventoryToString() {
        String inventoryText = "";
        for(int i = 0; i < items.length; ++i) {
            for(int j = 0; j < items[i].length; ++j) {
              if (items[i][j] != null)
                inventoryText += "Item: " + items[i][j].getName() + " ";
              else
                inventoryText += "Item: null ";
            }
            inventoryText += "\n";
        }
        return inventoryText;
    }
}
