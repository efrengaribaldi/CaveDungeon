package src.game;

import src.character.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {
    private static final long serialVersionUID = 1L;
    private Game game;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
        game = new Game(this, primaryStage);
        //loadFile();
        primaryStage.setTitle("CaveDungeon 0.3.99999999 ALPHA");
        primaryStage.show();
    }

    public void saveFile(Player player) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(findFile(player.getName()));
        ObjectOutputStream saveGame = new ObjectOutputStream(fileOut);
        saveGame.writeObject(game);
        saveGame.close();
    }

    public void loadFile(Player player) throws IOException, ClassNotFoundException {
        game = null;
        FileInputStream fileIn = new FileInputStream(findFile(player.getName()));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        game = (Game) in.readObject();

        System.out.println("Player: " + game.getPlayer().getName());
        in.close();
        fileIn.close();
    }

    public File findFile(String playerName) throws IOException {
        String classpath = System.getProperty("java.class.path");
        File file = new File(classpath + "/src/game/saves", playerName + ".atm");
        return file;
    }
}