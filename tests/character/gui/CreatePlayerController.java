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

public class CreatePlayerController {
    private int playerSelected;
    private Player newPlayer;
    private Game game;

    public CreatePlayerController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox gender;
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
    }

    @FXML
    private void createPlayer(ActionEvent event) {
        if (name.getText().equals("")) {
            System.out.println("Enter a name");
            return;
        } else if (gender.getSelectionModel().isEmpty()) {
            System.out.println("Choose a gender");
            return;
        } else if (playerSelected != 1 && playerSelected != 2) {
            System.out.println("Choose a type of player");
            return;
        }
        System.out.println(gender.getValue().toString());
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
        // Set the newPlayer to game
        game.setNewPlayer(newPlayer);
        game.playerTests();
        game.battleTests();
        game.setRoomScene();
    }

    private char genderSelected() {
        return (((String) (gender.getValue())).equals("Male")) ? 'M' : 'F';
    }

    public Player getPlayerCreated() {
        return newPlayer;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
