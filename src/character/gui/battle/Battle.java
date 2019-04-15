package src.character.gui.battle;

import src.character.Player;
import src.character.NPC;

import javafx.scene.Scene;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Battle extends Scene {
    private BattleController battleController;
    
    public Battle(Player player, NPC npc) throws Exception {
        super(new Pane());
        FXMLLoader loader = new FXMLLoader();
        URI fxmlDocPath = getClass().getResource("./battle.fxml").toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        Pane root = (Pane) loader.load(fxmlStream);
        battleController = loader.<BattleController>getController();
        battleController.initialize();
        this.setRoot(root);
    }
}