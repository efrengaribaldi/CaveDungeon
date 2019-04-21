package src.character.gui.battle;

import src.character.Player;
import src.character.NPC;
import src.game.Game;

import javafx.scene.Scene;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Battle extends Scene {
    public Battle(Player player, NPC npc, Game game) {
        super(new Pane());
        Pane root = new Pane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./battle.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (Pane) loader.load(fxmlStream);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        BattleController battleController = loader.<BattleController>getController();
        battleController.initialize(player, npc, game);
        this.setRoot(root);
    }
}