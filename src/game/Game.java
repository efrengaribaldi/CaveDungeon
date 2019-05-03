package src.game;

import src.character.gui.battle.Battle;
import src.character.gui.createplayer.CreatePlayer;
import src.character.NPC;
import src.character.Player;
import src.item.Armor;
import src.item.Potion;
import src.item.Weapon;
import src.item.gui.droppedItem.DroppedItemGUI;
import src.item.gui.inventory.InventoryGUI;
import src.map.gui.MapRender;
import src.map.Map;
import src.game.gui.Start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;

public class Game extends Application {
    private Map[] levels;
    private Player player;
    private Stage stage;
    private Scene mapRender;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        long gameSeed = System.currentTimeMillis();
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        stage.setScene(new Start(this));
        //Player is saved at the close of Game
        stage.setOnCloseRequest(event -> {
            try {
                if(findFile(player.getName(), 'P').delete() && findFile(player.getName(), 'M').delete()) {
                    System.out.println("Game saved successfully"); 
                    savePlayer();
                    saveMap();
                }
            } catch (IOException e) {
                System.out.println("File not found");
            } catch (NullPointerException e) {
                System.out.println("Game not saved | Player not loaded or created");
            }
        });
        stage.setTitle("CaveDungeon 1.O BETA");
        stage.show();
    }

    private void mapTests() {
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }

    private void playerTests() {
        System.out.println(player.playerToString());
        System.out.println("Your equipped weapon is: " + player.getInventory().getEquippedWeapon().getName());
        System.out.println("Limit HP: " + player.getLimitHp() + " Current HP: " + player.getHealthPoints());
        System.out.println("Limit Stamina: " + player.getLimitStamina() + " Current Stamina: " + player.getStamina());
        System.out.println(
                "Next Level EXP: " + player.getExpRequiredForNextLevel() + " Current EXP: " + player.getExperience());
    }

    //This method runs only when a new user is created
    public void setNewPlayerAndContinue(Player player) throws IOException, ClassNotFoundException {
        this.player = player;
        player.getInventory().equipWeapon(0);
        String classpath = System.getProperty("java.class.path");
        new File(classpath + "/src/game/saves/" + player.getName()).mkdir();
        savePlayer();
        saveMap();
        player.checkLevelUp(400);
        levels[0].setPlayer(player);
        mapTests();
        mapRender = new MapRender(this, levels[0]);
        playerTests();
        setRoomScene();
    }

    //This method runs only when an user is loaded
    public void setNewPlayerAndContinue() throws IOException, ClassNotFoundException {
        levels[0].setPlayer(player);
        mapTests();
        mapRender = new MapRender(this, levels[0]);
        playerTests();
        setRoomScene();
    }

    public Player getPlayer() {
        return player;
    }

    public void setCreatePlayerScene() {
        stage.setScene(new CreatePlayer(this));
    }

    public void setRoomScene() {
        stage.setScene(mapRender);
    }

    public void setInventoryScene() {
        stage.setScene(new InventoryGUI(this));
    }

    public void startBattle(NPC npc) {
        stage.setScene(new Battle(this.player, npc, this));
    }

    public void itemDropped(Weapon newWeapon) {
        stage.setScene(new DroppedItemGUI(this, newWeapon));
    }

    public void itemDropped(Potion newPotion) {
        stage.setScene(new DroppedItemGUI(this, newPotion));
    }

    public void itemDropped(Armor newArmor) {
        stage.setScene(new DroppedItemGUI(this, newArmor));
    }
    
    public void savePlayer() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(findFile(player.getName(), 'P'));
        ObjectOutputStream savePlayer = new ObjectOutputStream(fileOut);
        savePlayer.writeObject(player);
        savePlayer.close();
    }

    public void loadPlayer(String name) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(findFile(name, 'P'));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        player = (Player) in.readObject();
        System.out.println("Player loaded: " + player.getName());
        in.close();
        fileIn.close();
    }

    public void saveMap() throws IOException {
        try {
            int x = ((MapRender) mapRender).getXPos();
            int y = ((MapRender) mapRender).getYPos();
            levels[0].getRoom(x, y).removePlayer();
        }
        catch(NullPointerException exception) {
            System.out.println("Map created and saved");
        }
        FileOutputStream fileOut = new FileOutputStream(findFile(player.getName(), 'M'));
        ObjectOutputStream saveMap = new ObjectOutputStream(fileOut);
        saveMap.writeObject(levels);
        saveMap.close();
    }

    public void loadMap(String name) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(findFile(name, 'M'));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        levels = (Map[]) in.readObject();
        System.out.println("Map loaded");
        in.close();
        fileIn.close();
    }

    public File findFile(String name, char type) throws IOException {
        String classpath = System.getProperty("java.class.path");
        return (type == 'P') ? new File(classpath + "/src/game/saves/" + name + "/", "player.atm") : 
                new File(classpath + "/src/game/saves/" + name + "/", "map.atm");
    }
}
