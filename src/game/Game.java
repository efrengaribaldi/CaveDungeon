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
        stage.setScene(new CreatePlayer(this));
        //Player is saved at the close of Game
        stage.setOnCloseRequest(event -> {
            try {
                if(findPlayer(player.getName()).delete()) 
                    System.out.println("File deleted successfully"); 
                saveGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stage.setTitle("CaveDungeon 0.4.99999999 ALPHA");
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

    public void setNewPlayerAndContinue(Player player) throws IOException, ClassNotFoundException {
        this.player = player;
        checkAccount();
        levels[0].setPlayer(player);
        mapTests();
        mapRender = new MapRender(this, levels[0]);
        playerTests();
        setRoomScene();
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

    //Create class to save Map and Player
    public void checkAccount() throws IOException, ClassNotFoundException {
        if(findPlayer(player.getName()).exists())
            loadGame();
        else {
            player.getInventory().equipWeapon(0);
            saveGame();
        }
    }
    
    public void saveGame() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(findPlayer(player.getName()));
        ObjectOutputStream saveGame = new ObjectOutputStream(fileOut);
        saveGame.writeObject(player);
        saveGame.close();
    }

    public void loadGame() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(findPlayer(player.getName()));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        player = (Player) in.readObject();
        System.out.println("Player loaded: " + player.getName());
        in.close();
        fileIn.close();
    }

    public File findPlayer(String playerName) throws IOException {
        String classpath = System.getProperty("java.class.path");
        File file = new File(classpath + "/src/game/saves", playerName + ".atm");
        return file;
    }
}
