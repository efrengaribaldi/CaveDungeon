package src.game.gui;

import src.game.Game;

import javafx.scene.Scene;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Start extends Scene {

    public Start(Game game) {
        super(new Pane());

        Pane root = new Pane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./start.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (Pane) loader.load(fxmlStream);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        StartController startController = loader.<StartController>getController();
        startController.initialize(game);
        this.setRoot(root);
    }
}