package src.item.gui.droppedItem;

import src.game.Game;
import src.item.Inventory;
import src.item.Armor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DroppedArmorController {
    private Game game;
    private Inventory inventory;
    private Armor newArmor;

    @FXML
    private Label newArmorStats;
    @FXML
    private ImageView newArmorIV;
    @FXML
    private HBox armorDisplays, armorButtons;

    @FXML
    public void initialize(Game game, Armor newArmor) {
        this.game = game;
        inventory = game.getPlayer().getInventory();
        this.newArmor = newArmor;
        newArmorStats.setText(newArmor.getName() + "\n  " + newArmor.printDefense());
        newArmorIV.setImage(newArmor.render());

        boolean playerHasAnEmptySpace = false;
        for (int i = 0; i < 3; i++)
            if (inventory.getArmor(i) == null)
                playerHasAnEmptySpace = true;

        if (playerHasAnEmptySpace) {
            Button continueBtn = new Button("Continue");
            continueBtn.setFont(new Font(26));
            continueBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < 3; i++)
                        if (inventory.getArmor(i) == null) {
                            inventory.addItemToInventory(newArmor, i);
                            break;
                        }
                    game.setRoomScene();
                }
            });
            armorDisplays.getChildren().add(continueBtn);
        } else
            generateSwapForArmor();
    }

    @FXML
    private void dropNewArmor() {
        game.setRoomScene();
    }

    @FXML
    private void changeForArmor(int index) {
        inventory.addItemToInventory(newArmor, index);
        game.setRoomScene();
    }

    private void generateSwapForArmor() {
        for (int i = 0; i < 3; i++) {
            int j = i; // No funciona si no tiene eso (?)
            ImageView iv = new ImageView(inventory.getArmor(i).render());
            iv.setFitWidth(112);
            iv.setFitHeight(112);
            Label lbl = new Label(inventory.getArmor(i).getName() + "\n  " + inventory.getArmor(i).printDefense());
            lbl.setFont(new Font(16));
            lbl.setTextFill(Color.WHITE);
            HBox hBox = new HBox(iv, lbl);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefWidth(300);
            hBox.setSpacing(20);
            armorDisplays.getChildren().add(hBox);
            Button changeForThisArmor = new Button("Change for this armor");
            changeForThisArmor.setPrefWidth(240);
            changeForThisArmor.setPrefHeight(80);
            changeForThisArmor.setFont(new Font(22));
            changeForThisArmor.setTextAlignment(TextAlignment.CENTER);
            changeForThisArmor.setWrapText(true);
            changeForThisArmor.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    changeForArmor(j);
                }
            });
            armorButtons.getChildren().add(changeForThisArmor);
        }
    }
}