package src.item.gui;

import src.character.Player;
import src.item.Inventory;
import src.game.Game;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ProgressBar;

public class InventoryController {
    private Game game;
    private Player player;
    private Inventory inventory;

    @FXML
    private Label playerName, playerLevel, playerGender, playerType, health, stamina, currentLevel, nextLevel;
    @FXML
    private ProgressBar healthBar, staminaBar, expBar;
    @FXML
    private HBox weaponsHB, armorHB, potionsHB;

    public InventoryController() {
    }

    @FXML
    public void initialize(Game game) {
        this.game = game;
        player = this.game.getPlayer();
        inventory = player.getInventory();
        setupTopInfo();
        setupSideInfo();
        renderArmor();
        renderPotions();
        renderWeapons();
    }

    private void setupTopInfo() {
        playerName.setText(player.getName());
        healthBar.setProgress((double) player.getHealthPoints() / player.getLimitHp());
        health.setText("Health [" + player.getHealthPoints() + "/" + player.getLimitHp() + "]");
        staminaBar.setProgress((double) player.getStamina() / player.getLimitStamina());
        stamina.setText("Stamina [" + player.getStamina() + "/" + player.getLimitStamina() + "]");
    }

    private void setupSideInfo() {
        currentLevel.setText("" + player.getLevel());
        nextLevel.setText("" + (player.getLevel() + 1));
        expBar.setProgress((double) player.getExperience() / player.getExpRequiredForNextLevel());
        playerLevel.setText("Level: " + player.getLevel());
        playerType.setText("Type: " + player.getType());
        playerGender.setText("Gender: " + Character.toString(player.getGender()).toUpperCase());
    }

    private void renderWeapons() {
        Button switchWeaponsBtn = (Button) weaponsHB.getChildren().get(0);
        weaponsHB.getChildren().clear();
        weaponsHB.getChildren().add(switchWeaponsBtn);
        for (int i = 0; i < 2; i++) {
            ImageView iv = new ImageView(inventory.getWeapon(i).render());
            Label lbl = new Label(inventory.getWeapon(i).getName() + "\n" + inventory.getWeapon(i).printAbilities());
            lbl.setTextFill(Color.WHITE);
            HBox hBox = new HBox(iv, lbl);
            hBox.setAlignment(Pos.CENTER);
            weaponsHB.getChildren().add(hBox);
        }
    }

    private void renderArmor() {
        armorHB.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            if (inventory.getArmor(i) == null)
                continue;
            ImageView iv = new ImageView(inventory.getArmor(i).render());
            Label lbl = new Label(inventory.getArmor(i).getName());
            lbl.setTextFill(Color.WHITE);
            HBox hBox = new HBox(iv, lbl);
            hBox.setAlignment(Pos.CENTER);
            armorHB.getChildren().add(hBox);
        }
        if (armorHB.getChildren().size() == 0)
            armorHB.getChildren().add(emptyLabel());
    }

    private void renderPotions() {
        potionsHB.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            if (inventory.getPotion(i) == null)
                continue;
            ImageView iv = new ImageView(inventory.getPotion(i).render());
            Label lbl = new Label(inventory.getPotion(i).getName());
            lbl.setTextFill(Color.WHITE);
            HBox hBox = new HBox(iv, lbl);
            hBox.setAlignment(Pos.CENTER);
            potionsHB.getChildren().add(hBox);
        }
        if (potionsHB.getChildren().size() == 0)
            potionsHB.getChildren().add(emptyLabel());
    }

    private Label emptyLabel() {
        Label lbl = new Label("* Empty *");
        lbl.setTextFill(Color.WHITE);
        return lbl;
    }

    @FXML
    private void switchWeapons(ActionEvent event) {
        inventory.switchEquippedWeapons();
        renderWeapons();
    }

    @FXML
    private void exitInventory(ActionEvent event) {
        game.setRoomScene();
    }
}
