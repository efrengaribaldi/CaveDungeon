package src.character.gui;

import src.character.Player;
import src.character.player.*;
import src.item.Inventory;
import src.game.Game;

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

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    public CreatePlayerController() {
    }

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

        switch (playerSelected) {
        case 1:
            newPlayer = new Melee(name.getText(), 20, genderSelected(), new Inventory(), 1.50);
            break;
        case 2:
            newPlayer = new Mage(name.getText(), 25, genderSelected(), new Inventory(), 1);
            break;
        default:
            newPlayer = null;
            System.out.println("Player not found!");
        }
        // Set the newPlayer to game
        game.setNewPlayer(newPlayer);
        game.playersrc();
        game.battlesrc();
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
