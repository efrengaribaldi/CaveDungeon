package src.item.gui.droppedItem;

import src.game.Game;
import src.item.Inventory;
import src.item.Weapon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DroppedWeaponController {
    private Game game;
    private Inventory inventory;
    private Weapon newWeapon;

    @FXML
    private Label newWeaponStats, weapon1Stats, weapon2Stats;
    @FXML
    private ImageView newWeaponIV, weapon1IV, weapon2IV;

    @FXML
    public void initialize(Game game, Weapon newWeapon) {
        this.game = game;
        inventory = game.getPlayer().getInventory();
        this.newWeapon = newWeapon;
        String str = newWeapon.getName() + "\n" + newWeapon.printAbilities();
        newWeaponStats.setText(str);
        newWeaponIV.setImage(newWeapon.render());
        weapon1Stats.setText(inventory.getWeapon(0).getName() + "\n" + inventory.getWeapon(0).printAbilities());
        weapon1IV.setImage(inventory.getWeapon(0).render());
        weapon2Stats.setText(inventory.getWeapon(1).getName() + "\n" + inventory.getWeapon(1).printAbilities());
        weapon2IV.setImage(inventory.getWeapon(1).render());
    }

    @FXML
    private void dropNewWeapon() {
        game.setRoomScene();
    }

    @FXML
    private void changeForWeapon1() {
        inventory.addItemToInventory(newWeapon, 0);
        game.setRoomScene();
    }

    @FXML
    private void changeForWeapon2() {
        inventory.addItemToInventory(newWeapon, 1);
        game.setRoomScene();
    }
}