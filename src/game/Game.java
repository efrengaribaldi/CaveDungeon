package src.game;

import src.map.*;
import src.map.gui.MapRender;
import src.character.*;
import src.character.gui.CreatePlayer;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.item.gui.*;

import java.util.Scanner;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Game {
    Scanner sc = new Scanner(System.in);
    private Map[] levels;
    private Player player;
    private Stage stage;
    private Scene inventoryGui;

    public Game(Stage stage) throws Exception {
        this.stage = stage;
        long gameSeed = System.currentTimeMillis();
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        mapTests();
        stage.setScene(new CreatePlayer(this));
        // setRoomScene();
    }

    private void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    private void playerTests() {
        System.out.println(player.playerToString());
        player.equipWeapon();
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
            battle = new Battle(player, newEnemy);
            System.out.println("\nStart new battle? (Y or N)");
            battleAgain = sc.next().charAt(0);
        } while (battleAgain == 'Y' || battleAgain == 'y');
    }

    public void setNewPlayerAndContinue(Player player) throws Exception {
        this.player = player;
        inventoryGui = new InventoryGUI(this);
        levels[0].setPlayer(player);
        playerTests();
        // player.checkLevelUp(200);
        //battleTests();
        setRoomScene();
        //setInventoryScene();
    }

    public void setInventoryAndContinue() throws Exception {
;
    }

    public Player getPlayer() {
        return player;
    }

    private void setRoomScene() {
        stage.setScene(new MapRender(this, levels[0]));
    }

    private void setInventoryScene() {
        stage.setScene(inventoryGui);
    }
}
