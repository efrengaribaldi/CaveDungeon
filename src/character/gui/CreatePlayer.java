package src.character.gui;

import src.game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CreatePlayer extends Scene {
    private Game game;

    public CreatePlayer(Game game) throws Exception {
        super(new HBox());
        this.game = game;
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        URI fxmlDocPath = getClass().getResource("./createPlayer.fxml").toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        CreatePlayerController playerController = loader.<CreatePlayerController>getController();
        playerController.setGame(game);
        this.setRoot(root);
    }
}
