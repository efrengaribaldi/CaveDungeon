package src.game.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import src.game.Game;

public class StartController {
    private Game game;

    @FXML
    Pane wrapPane, selectPane, loadPane;
    @FXML
    TextField name;

    public StartController() {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setController(this);
        try {
            loadPane = (Pane) loader.load(loadFXML("./loadPane.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileInputStream loadFXML(String path) throws URISyntaxException, FileNotFoundException {
        URI fxmlDocPath = getClass().getResource(path).toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        return fxmlStream;
    }

    @FXML
    public void initialize(Game game) {
        this.game = game;
    }

    @FXML
    private void newGame(ActionEvent event) {
        game.setCreatePlayerScene();
    }

    @FXML
    private void loadGame(ActionEvent event) {
        changePane(selectPane, loadPane);
    }

    @FXML
    private void continueBtn(ActionEvent event) throws ClassNotFoundException, IOException {
        checkAccount();
    }

    @FXML
    private void backBtn(ActionEvent event) {
        changePane(loadPane, selectPane);
    }

    public void checkAccount() throws IOException, ClassNotFoundException {
        if(findFile(name.getText(), 'P').exists()) {
            game.loadPlayer(name.getText());
            game.loadMap(name.getText());
            game.setNewPlayerAndContinue();
        }
        else {
            System.out.println("User doesn't exist");
        }
    }

    public File findFile(String name, char type) throws IOException {
        String classpath = System.getProperty("java.class.path");
        return (type == 'P') ? new File(classpath + "/src/game/saves/" + name + "/", "player.atm") : 
                new File(classpath + "/src/game/saves/" + name + "/", "map.atm");
    }

    private void changePane(Pane paneRemoved, Pane paneAdded) {
        wrapPane.getChildren().remove(paneRemoved);
        wrapPane.getChildren().add(paneAdded);
    }
}