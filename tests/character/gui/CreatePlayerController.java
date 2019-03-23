package tests.character.gui;

import tests.character.Player;
import tests.character.player.*;
import tests.item.Inventory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

public class CreatePlayerController {

    public CreatePlayerController() {

    }

    private int playerSelected;
    private Player newPlayer;

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
    }

    @FXML
    private void createPlayer(ActionEvent event) {
        switch (playerSelected) {
        case 1:
            newPlayer = new Melee(name.getText(), 30, genderSelected(), new Inventory());
            break;
        case 2:
            newPlayer = new Mage(name.getText(), 30, genderSelected(), new Inventory());
            break;
        default:
            newPlayer = null;
            System.out.println("Player not found!");
        }
    }

    private char genderSelected() {
        return (genderBox.getValue().toString() == "Male") ? 'M' : 'F';
    }

}
