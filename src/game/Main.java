package src.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(primaryStage);
        primaryStage.setTitle("CaveDungeon 0.1.99999999 ALPHA");
        primaryStage.show();
    }
}