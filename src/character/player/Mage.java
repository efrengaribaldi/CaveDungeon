package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.armor.*;
import src.item.weapon.*;
import src.item.potion.*;

import javafx.scene.image.Image;

public class Mage extends Player {
    private static final long serialVersionUID = 1L;

    public Mage(String name, char gender) {
        super(name, 20, gender, new Inventory(), 1.0, 1.2);
        this.inventory.addItemToInventory(new EnchantedBook(15, 5), 0);
        this.inventory.addItemToInventory(new Wand(15, 5), 1);
        this.inventory.addItemToInventory(new Necklace(2), 0);
        this.inventory.addItemToInventory(new HealthPotion(), 0);
        this.inventory.addItemToInventory(new StaminaPotion(), 1);
        this.inventory.addItemToInventory(new StaminaPotion(), 2);
    }

    public Mage() {
    }

    @Override
    public String getType() {
        return "Mage";
    }

    @Override
    public Image render() {
        String path = "./img/mage_" + getGender() + ".gif";
        return new Image(getClass().getResource(path).toString());
    }
}
