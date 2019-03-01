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
        int playerSelector;
        System.out.print("Select your player mage(1) or melee(2): ");
        playerSelector = scanner.nextInt();
        System.out.print("Write your name: ");
        name = scanner.nextLine();
        System.out.print("Select your gender (M or F): ");
        gender = scanner.next().charAt(0);
        Player newPlayer = (playerSelector == 1) ? new Melee(name, 30, gender, 0, new Inventory()) : new Mage(name, 30, gender, 0, new Inventory());
    }

}
