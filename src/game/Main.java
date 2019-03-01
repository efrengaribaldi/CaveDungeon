package src.game;

import java.util.Scanner;
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
    private static void inventoryTests() {
        Inventory newInventory = new Inventory();
        Weapon elvenSword = new Sword();
        Weapon bow = new Bow();
        Potion healthPotion = new HealthPotion();
        newInventory.addItemToInventory(elvenSword, 0);
        newInventory.addItemToInventory(bow, 1);
        newInventory.addItemToInventory(healthPotion, 2);
        System.out.println(newInventory.inventoryToString());
        System.out.println("Which weapon do you want to equip?");
        System.out.println(newInventory.printWeapons());
        newInventory.equipWeapon(1);
        System.out.println(newInventory.printWeapons());

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game newGame = new Game();
        // inventoryTests();
    }
}
