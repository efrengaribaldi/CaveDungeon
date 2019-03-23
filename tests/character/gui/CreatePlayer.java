package tests.character.gui;

import tests.game.Game;

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
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        // String fxmlDocPath = "createPlayer.fxml";
        // FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        URI fxmlDocPath = getClass().getResource("./createPlayer.fxml").toURI();
        System.out.println(getClass().getResource("./createPlayer.fxml").toString());
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));

        // Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        this.setRoot(root);
        this.game = game;
    }

}
