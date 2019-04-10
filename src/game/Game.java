package src.game;

import src.map.*;
import src.character.*;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.character.gui.*;

import java.util.Scanner;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Game {
    Scanner sc = new Scanner(System.in);
    private Map[] levels;
    private Player newPlayer;
    private Stage stage;

    public Game(Stage stage) throws Exception {
        this.stage = stage;
        long gameSeed = System.currentTimeMillis();
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        mapTests();
        Scene createPlayer = new CreatePlayer(this);
        stage.setScene(createPlayer);
    }

    private void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    private void playerTests() {
        System.out.println(newPlayer.playerToString());
        newPlayer.selectWeapon();
    }

    private void battleTests() {
        char battleAgain;
        Enemy newEnemy;
        Battle battle;
        do {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Select your enemy: (1) Zombie, (2) Skeleton, (3) Chort, (4) Swampy, (5) Necromancer");
            switch (sc.nextInt()) {
            case 1:
                newEnemy = new Zombie();
                break;
            case 2:
                newEnemy = new Skeleton();
                break;
            case 3:
                newEnemy = new Chort();
                break;
            case 4:
                newEnemy = new Swampy();
                break;
            case 5:
                newEnemy = new Necromancer();
                break;
            default:
                newEnemy = null;
                break;
            }
            System.out.println("------------------------------------------------------------------------------------");
            battle = new Battle(newPlayer, newEnemy);
            System.out.println("\nStart new battle? (Y or N)");
            battleAgain = sc.next().charAt(0);
        } while (battleAgain == 'Y' || battleAgain == 'y');
    }

    public void setNewPlayerAndContinue(Player newPlayer) {
        this.newPlayer = newPlayer;
        playerTests();
        battleTests();
        setRoomScene();
    }

    public Player getNewPlayer() {
        return newPlayer;
    }

    private void setRoomScene() {
        Pane p = levels[0].getRoom(levels[0].startX, levels[0].startY).render();
        Scene scene = new Scene(p);
        stage.setScene(scene);
    }
}
