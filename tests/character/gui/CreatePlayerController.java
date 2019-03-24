package tests.character.gui;

import tests.character.Player;
import tests.character.player.*;
import tests.item.Inventory;
import tests.game.Game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class CreatePlayerController {
    private int playerSelected;
    private Player newPlayer;
    private Game game;

    public CreatePlayerController() {

    }

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox genderBox;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    @FXML
    private void selectMelee(ActionEvent event) {
        playerSelected = 1;
        System.out.println("Player selected: Melee");
    }

    @FXML
    private void selectMage(ActionEvent event) {
        playerSelected = 2;
        System.out.println("Player selected: Mage");
        System.out.println(" ok" + genderSelected());
    }

    @FXML
    private void createPlayer(ActionEvent event) {
        switch (playerSelected) {
        case 1:
            newPlayer = new Melee(name.getText(), 25, genderSelected(), new Inventory());
            break;
        case 2:
            newPlayer = new Mage(name.getText(), 25, genderSelected(), new Inventory());
            break;
        default:
            newPlayer = null;
            System.out.println("Player not found!");
        }
        //Set the newPlayer to game
        game.setNewPlayer(newPlayer);
        //game.getGameStage().setTitle("Test");
        //game.getGameStage().setScene(new Scene(new HBox(new Label("ok"))));
        //game.getGameStage().show();
        game.playerTests();
        game.battleTests();
    }

    private char genderSelected() {
        return (genderBox.getValue().toString().equals("Male")) ? 'M' : 'F';
    }

    public Player getPlayerCreated() {
      return newPlayer;
    }

    public void setGame(Game game) {
      this.game = game;
    }

}
