package src.item.gui.inventory;

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
    public InventoryGUI(Game game) {
        super(new Pane());
        Pane root = new Pane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./inventory.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (Pane) loader.load(fxmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InventoryController inventoryController = loader.<InventoryController>getController();
        inventoryController.initialize(game);
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.E)
                    game.setRoomScene();
            }
        });
        this.setRoot(root);
    }
}
