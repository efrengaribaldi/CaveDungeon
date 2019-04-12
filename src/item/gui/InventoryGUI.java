package src.item.gui;

import src.game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class InventoryGUI extends Scene {
    private Game game;
    private InventoryController inventoryController;

    public InventoryGUI(Game game) throws Exception {
        super(new Pane());
        this.game = game;
        FXMLLoader loader = new FXMLLoader();
        URI fxmlDocPath = getClass().getResource("./inventory.fxml").toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        Pane root = (Pane) loader.load(fxmlStream);
        inventoryController = loader.<InventoryController>getController();
        inventoryController.setGame(game);
        this.setRoot(root);
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }
}
