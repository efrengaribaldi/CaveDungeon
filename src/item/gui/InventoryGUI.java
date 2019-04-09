package src.item.gui;

import src.game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;

public class InventoryGUI extends Scene {
    private Game game;

    public InventoryGUI(Game game) throws Exception {
        super(new HBox());
        this.game = game;
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        URI fxmlDocPath = getClass().getResource("./inventory.fxml").toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        Pane root = (Pane) loader.load(fxmlStream);
        InventoryController inventoryController = loader.<InventoryController>getController();
        inventoryController.setGame(game);
        this.setRoot(root);
    }
}
