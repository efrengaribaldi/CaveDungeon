import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class CreatePlayer extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Path to the FXML File
        String fxmlDocPath = "createPlayer.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        //Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        //Create the Scene
        Scene scene = new Scene(root);
        //Set the scene to the stage
        stage.setScene(scene);
        //Set the title to the stage
        stage.setTitle("Create Player");
        //Display the stage
        stage.show();
    }

}
