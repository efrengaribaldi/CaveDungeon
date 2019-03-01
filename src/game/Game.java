package src.game;

import java.util.Scanner;
import src.map.*;
import src.character.*;
import src.character.player.*;
import src.character.player.mage.Mage;
import src.character.player.melee.Melee;
import src.item.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
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
        createPlayer();
    }

    void createPlayer() {
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
                System.out.println("Personaje no encontrado!");
        }
    }
}
