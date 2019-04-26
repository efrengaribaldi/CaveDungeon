package src.character.gui.createplayer;

import src.character.Player;
import src.character.player.*;
import src.game.Game;

import java.io.IOException;

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

    public CreatePlayerController() {
    }

    @FXML
    private void selectMelee(ActionEvent event) {
        playerSelected = 1;
    }

    @FXML
    private void selectMage(ActionEvent event) {
        playerSelected = 2;
    }

    @FXML
    private void createPlayer(ActionEvent event) throws ClassNotFoundException, IOException {
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
            newPlayer = new Melee(name.getText(), genderSelected());
            break;
        case 2:
            newPlayer = new Mage(name.getText(), genderSelected());
            break;
        }
        // Set the newPlayer to game
        game.setNewPlayerAndContinue(newPlayer);
    }

    private char genderSelected() {
        return (((String) gender.getValue()).equals("Male")) ? 'm' : 'f';
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
