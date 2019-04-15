package src.game;

import src.map.*;
import src.map.gui.MapRender;
import src.character.*;
import src.character.gui.createplayer.CreatePlayer;
import src.character.gui.battle.Battle;
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
    private Scene mapRender;
    private Scene battle;

    public Game(Stage stage) throws Exception {
        this.stage = stage;
        long gameSeed = System.currentTimeMillis();
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        stage.setScene(new CreatePlayer(this));
    }

    private void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    private void playerTests() {
        System.out.println(player.playerToString());
        player.equipWeapon();
        System.out.println("Limit HP: " + player.getLimitHp() + " Current HP: " + player.getHealthPoints());
        System.out.println("Limit Stamina: " + player.getLimitStamina() + " Current Stamina: " + player.getStamina());
        System.out.println(
                "Next Level EXP: " + player.getExpRequiredForNextLevel() + " Current EXP: " + player.getExperience());
    }

    private void battleTests() {
        char battleAgain;
        Enemy newEnemy;
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
            // Battle battle = new Battle(player, newEnemy);
            System.out.println("\nStart new battle? (Y or N)");
            battleAgain = sc.next().charAt(0);
        } while (battleAgain == 'Y' || battleAgain == 'y');
    }

    public void setNewPlayerAndContinue(Player player) throws Exception {
        this.player = player;
        levels[0].setPlayer(player);
        player.checkLevelUp(200); // for testing purposes
        mapTests();
        mapRender = new MapRender(this, levels[0]);
        playerTests();
        setRoomScene();
        // Battle instantiated for tests
        battle = new Battle(player, new Zombie());
        // setBattleScene();
    }

    public Player getPlayer() {
        return player;
    }

    public void setRoomScene() {
        stage.setScene(mapRender);
    }

    public void setInventoryScene() {
        stage.setScene(new InventoryGUI(this));
    }

    // Battle Scene tests
    public void setBattleScene() {
        stage.setScene(battle);
    }

    public void startBattle(Player player, NPC npc) {
        stage.setScene(battle);
    }
}
