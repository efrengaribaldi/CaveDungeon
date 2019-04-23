package src.item.gui.droppedItem;

import src.game.Game;
import src.item.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class DroppedItemGUI extends Scene {
    public DroppedItemGUI(Game game, Weapon newWeapon) {
        super(new Pane());
        Pane root = new Pane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./droppedWeapon.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (Pane) loader.load(fxmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DroppedWeaponController droppedWeaponController = loader.<DroppedWeaponController>getController();
        droppedWeaponController.initialize(game, newWeapon);
        this.setRoot(root);
    }

    public DroppedItemGUI(Game game, Armor newArmor) {
        super(new Pane());
        Pane root = new Pane();
        FXMLLoader loader = new FXMLLoader();
        try {
            URI fxmlDocPath = getClass().getResource("./droppedArmor.fxml").toURI();
            FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
            root = (Pane) loader.load(fxmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DroppedArmorController droppedArmorController = loader.<DroppedArmorController>getController();
        droppedArmorController.initialize(game, newArmor);
        this.setRoot(root);
    }

    public DroppedItemGUI(Game game, Potion newPotion) {
        super(new Pane());
    }
}
