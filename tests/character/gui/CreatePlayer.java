import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class CreatePlayer extends Scene {
    private Game game;

    public CreatePlayer(Game game) {
        //Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Path to the FXML File
        String fxmlDocPath = "createPlayer.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        //Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        //Create the Scene
        this.add(root);

        this.game = game;
    }



}
