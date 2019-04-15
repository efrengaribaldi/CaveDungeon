package src.character.gui.battle;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;

public class BattleController {
    @FXML
    private Pane wrapperPane;
    @FXML
    private Pane selectOption, selectAttack, selectPotion, playerAttack, enemyAttack;

    public BattleController() {
        FXMLLoader[] loader = new FXMLLoader[4];
        for (int i = 0; i < loader.length; ++i) {
            loader[i] = new FXMLLoader();
            loader[i].setBuilderFactory(new JavaFXBuilderFactory());
            loader[i].setController(this);
        }
        try {
            selectAttack = (Pane) loader[0].load(loadFXML("./selectAttack.fxml"));
            selectPotion = (Pane) loader[1].load(loadFXML("./selectPotion.fxml"));
            playerAttack = (Pane) loader[2].load(loadFXML("./playerAttack.fxml"));
            enemyAttack = (Pane) loader[3].load(loadFXML("./enemyAttack.fxml"));
        } catch (Exception e) {
            System.out.println("ERROR: Battle Controller couldn't start");
        }
    }

    public FileInputStream loadFXML(String path) throws URISyntaxException, FileNotFoundException {
        URI fxmlDocPath = getClass().getResource(path).toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        return fxmlStream;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    private void attack(ActionEvent event) {
        wrapperPane.getChildren().remove(selectOption);
        wrapperPane.getChildren().add(selectAttack);
    }

    @FXML
    private void usePotion(ActionEvent event) {

    }

    @FXML
    private void equipWeapon(ActionEvent event) {

    }

    @FXML
    private void selectAbility(ActionEvent event) {
        System.out.println("It works");
    }
}
