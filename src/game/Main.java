package src.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game(primaryStage);
        primaryStage.setTitle("CaveDungeon 0.1.290324 OMEGA");
        primaryStage.show();
    }
}