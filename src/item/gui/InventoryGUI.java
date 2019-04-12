package src.item.gui;

import src.game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class InventoryGUI extends Scene {
    private InventoryController inventoryController;

    public InventoryGUI(Game game) throws Exception {
        super(new Pane());
        FXMLLoader loader = new FXMLLoader();
        URI fxmlDocPath = getClass().getResource("./inventory.fxml").toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        Pane root = (Pane) loader.load(fxmlStream);
        inventoryController = loader.<InventoryController>getController();
        inventoryController.setGame(game);
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.E)
                    game.setRoomScene();
            }
        });
        this.setRoot(root);
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }
}
