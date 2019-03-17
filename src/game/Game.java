package src.game;

import java.util.Scanner;
import src.map.*;
import src.character.*;
import src.character.player.*;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.item.*;
import src.item.Weapon;
import src.item.Potion;
import src.item.potion.HealthPotion;
import src.item.weapon.*;

public class Game {
    Scanner scanner = new Scanner(System.in);

    private Map[] levels;
    public Player newPlayer;

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

    void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    void createPlayer() {
        String name;
        char gender;
        System.out.println("Write your name: ");
        name = scanner.nextLine();
        System.out.println("Select your gender (M or F): ");
        gender = scanner.next().charAt(0);
        System.out.println("Select your player Melee(1) or Mage(2): ");
        switch (scanner.nextInt()) {
        case 1:
            newPlayer = new Melee(name, 25, gender, new Inventory());
            break;
        case 2:
            newPlayer = new Mage(name, 25, gender, new Inventory());
            break;
        default:
            newPlayer = null;
            System.out.println("Player not found!");
        }
    }

    void playerTests() {
        Weapon elvenSword = new Sword();
        Weapon bow = new Bow();
        Potion healthPotion = new HealthPotion();
        Scanner scanner = new Scanner(System.in);
        int selectedWeapon;
        newPlayer.getInventory().addItemToInventory(elvenSword, 0);
        newPlayer.getInventory().addItemToInventory(bow, 1);
        newPlayer.getInventory().addItemToInventory(healthPotion, 0);
        System.out.println("Which weapon do you want to equip?");
        System.out.println(newPlayer.getInventory().printWeapons());
        selectedWeapon = scanner.nextInt();
        newPlayer.getInventory().equipWeapon(selectedWeapon);
        System.out.println(newPlayer.getInventory().printWeapons());

        System.out.println(newPlayer.playerToString());
    }

    void battleTests() {
        char startBattle;
        Enemy newEnemie;
        do {
            System.out.println("Select your enemy: (1) Zombie, (2) Skeleton, (3) Chort, (4) Swampy, (5) Necromancer");
            switch (scanner.nextInt()) {
            case 1:
                newEnemie = new Zombie();
                break;
            case 2:
                newEnemie = new Skeleton();
                break;
            case 3:
                newEnemie = new Chort();
                break;
            case 4:
                newEnemie = new Swampy();
                break;
            case 5:
                newEnemie = new Necromancer();
                break;
            default:
                newEnemie = null;
                break;
            }
            System.out.println("______________START BATTLE________________");
            Battle.startBattle(newPlayer, newEnemie);
            System.out.println("\nStart new battle? (Y or N)");
            startBattle = scanner.next().charAt(0);
        } while (startBattle == 'Y');

    }

}
