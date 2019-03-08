package src.game;

import java.util.Scanner;
import src.map.*;
import src.character.*;
import src.character.player.*;
import src.character.npc.*;
import src.character.player.mage.Mage;
import src.character.player.melee.Melee;
import src.character.npc.enemy.Enemy;
import src.character.npc.enemy.necromancer.Necromancer;
import src.item.*;
import src.item.weapon.Weapon;
import src.item.potion.Potion;
import src.item.potion.healthpotion.HealthPotion;
import src.item.weapon.sword.Sword;
import src.item.weapon.bow.Bow;



public class Game {
    private Map[] levels;
    public Player newPlayer;

    void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    public Game() {
        long gameSeed = System.currentTimeMillis();
        // long gameSeed = 1550961460665L;
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        mapTests();
        System.out.println("Input the values for create player (!)");
        createPlayer();
        playerTests();
        battleTests();
    }

    void playerTests() {
        Weapon elvenSword = new Sword();
        Weapon bow = new Bow();
        Potion healthPotion = new HealthPotion();
        newPlayer.getInventory().addItemToInventory(elvenSword, 0);
        newPlayer.getInventory().addItemToInventory(bow, 1);
        newPlayer.getInventory().addItemToInventory(healthPotion, 2);
        System.out.println("Which weapon do you want to equip?");
        System.out.println(newPlayer.getInventory().printWeapons());
        newPlayer.getInventory().equipWeapon(0);

        System.out.println(newPlayer.playerToString());
    }

    void battleTests() {
        System.out.println("______________START BATTLE________________");
        Enemy necromancer = new Necromancer();
        Battle.startBattle(newPlayer, necromancer);
    }
    void createPlayer() {
        Scanner scanner = new Scanner(System.in);
        String name;
        char gender;
        System.out.print("Write your name: ");
        name = scanner.nextLine();
        System.out.print("Select your gender (M or F): ");
        gender = scanner.next().charAt(0);
        System.out.print("Select your player Melee(1) or Mage(2): ");
        switch(scanner.nextInt()) {
            case 1:
                newPlayer = new Melee(name, 30, gender, 0, new Inventory());
                break;
            case 2:
                newPlayer = new Mage(name, 30, gender, 0, new Inventory());
                break;
            default:
                newPlayer = null;
                System.out.println("Player not found!");
        }
    }
}
