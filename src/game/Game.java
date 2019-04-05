package src.game;

import src.map.*;
import src.character.*;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.character.gui.*;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Game extends Application {
    Scanner sc = new Scanner(System.in);

    private Map[] levels;
    public Player newPlayer;
    public static Stage gameStage;

    public static void main(String[] args) {
        Application.launch(args);
        // Print the name without errors
        // System.out.println(newPlayer.getName());
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameStage = stage;
        Scene createPlayer = new CreatePlayer(this);
        gameStage.setScene(createPlayer);
        gameStage.setTitle("CaveDungeon 0.1.190324 OMEGA");
        gameStage.show();
        // ERROR
        // System.out.println(newPlayer.getName());
    }

    public Game() {
        long gameSeed = System.currentTimeMillis();
        // long gameSeed = 1550961460665L;
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        mapsrc();
    }

    void mapsrc() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    public void playersrc() {

        Scanner sc = new Scanner(System.in);
        int selectedWeapon;

        System.out.println("Which weapon do you want to equip?");
        System.out.println(newPlayer.getInventory().printWeapons());
        selectedWeapon = sc.nextInt();
        newPlayer.getInventory().equipWeapon(selectedWeapon);
        System.out.println(newPlayer.getInventory().printWeapons());

        System.out.println(newPlayer.playerToString());
    }

    public void battlesrc() {
        char startBattle;
        Enemy newEnemie;
        do {
            System.out.println("-----------");
            System.out.println("Select your enemy: (1) Zombie, (2) Skeleton, (3) Chort, (4) Swampy, (5) Necromancer");
            System.out.println("-----------");
            switch (sc.nextInt()) {
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

            Battle.startBattle(newPlayer, newEnemie);
            System.out.println("\nStart new battle? (Y or N)");
            startBattle = sc.next().charAt(0);
        } while (startBattle == 'Y' || startBattle == 'y');

    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }

    public Player getNewPlayer() {
        return newPlayer;
    }

    public static Stage getGameStage() {
        return gameStage;
    }

    public void setRoomScene() {
        Pane p = levels[0].getRoom(levels[0].startX, levels[0].startY).render();
        Scene scene = new Scene(p);
        gameStage.setScene(scene);
    }
}
