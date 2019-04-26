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

import java.io.IOException;
import java.io.Serializable;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private Main main;
    private Map[] levels;
    private Player player;
    transient private Stage stage;
    private Scene mapRender;

    public Game(Main main, Stage stage) {
        this.stage = stage;
        this.main = main;
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
        player.getInventory().equipWeapon(0);
        System.out.println("You equipped weapon is: " + player.getInventory().getEquippedWeapon().getName());
        System.out.println("Limit HP: " + player.getLimitHp() + " Current HP: " + player.getHealthPoints());
        System.out.println("Limit Stamina: " + player.getLimitStamina() + " Current Stamina: " + player.getStamina());
        System.out.println(
                "Next Level EXP: " + player.getExpRequiredForNextLevel() + " Current EXP: " + player.getExperience());
    }

    public void setNewPlayerAndContinue(Player player) throws IOException {
        this.player = player;
        levels[0].setPlayer(player);
        mapTests();
        mapRender = new MapRender(this, levels[0]);
        playerTests();
        setRoomScene();
        saveGame(player);
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

    public void startBattle(Player player, NPC npc) {
        stage.setScene(new Battle(player, npc, this));
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

    public void saveGame(Player player) throws IOException {
        main.saveFile(player);
    }
}
