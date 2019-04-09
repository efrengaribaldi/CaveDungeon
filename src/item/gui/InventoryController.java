package src.item.gui;

import src.character.Player;
import src.character.player.*;
import src.item.Inventory;
import src.game.Game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class InventoryController {
    private Player player;
    private Game game;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    public InventoryController() {
        
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
