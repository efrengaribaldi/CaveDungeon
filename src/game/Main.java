package src.game;

import src.item.*;
import src.item.armor.Armor;
import src.item.chest.Chest;
import src.item.key.Key;
import src.item.potion.Potion;
import src.item.potion.healthpotion.HealthPotion;
import src.item.weapon.Weapon;
import src.item.weapon.sword.Sword;
import src.item.weapon.bow.Bow;


public class Main {
    public static void main(String[] args) {
        Game newGame = new Game();


        //Inventory Tests
        Inventory newInventory = new Inventory();
        Weapon elvenSword = new Sword();
        Weapon bow = new Bow();
        //Potion healthPotion = new HealthPotion();
        newInventory.addItemToInventory(elvenSword, 4, 0);
        newInventory.addItemToInventory(bow, 4, 1);
        //newInventory.addItemToInventory(healthPotion, 4, 1);
        System.out.println(newInventory.inventoryToString());

    }
}
