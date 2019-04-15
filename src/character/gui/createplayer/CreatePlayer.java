package src.character.gui.createplayer;

import src.game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CreatePlayer extends Scene {

    public CreatePlayer(Game game) {
        super(new HBox());
        AnchorPane root = new AnchorPane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./createPlayer.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (AnchorPane) loader.load(fxmlStream);
        } catch (Exception e) {
            System.out.println("ERROR: Create PLayer couldn't be started");
        }
        CreatePlayerController playerController = loader.<CreatePlayerController>getController();
        playerController.setGame(game);
        this.setRoot(root);
    }
}