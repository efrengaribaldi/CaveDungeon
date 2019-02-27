package src.game;

import src.item.*;
import src.item.armor.Armor;
import src.item.chest.Chest;
import src.item.key.Key;
import src.item.potion.Potion;
import src.item.weapon.Weapon;
import src.item.weapon.sword.Sword;

public class Main {
    public static void main(String[] args) {
        Game newGame = new Game();


        //Inventory Tests
        Inventory newInventory = new Inventory();
        Weapon ElvenSword = new Sword();
        newInventory.addItemToInventory(ElvenSword, 4, 0);
        System.out.println(newInventory.inventoryToString());
        
    }
}
