package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.armor.*;
import src.item.weapon.*;
import src.item.potion.*;

import javafx.scene.image.Image;

public class Melee extends Player {
    public Melee(String name, char gender) {
        super(name, 15, gender, new Inventory(), 1.2, 1.0);
        this.inventory.addItemToInventory(new Sword(15, 5), 0);
        this.inventory.addItemToInventory(new Bow(15, 5), 1);
        this.inventory.addItemToInventory(new Shield(2), 0);
        this.inventory.addItemToInventory(new Shield(2), 1);
        this.inventory.addItemToInventory(new Shield(2), 2);
        this.inventory.addItemToInventory(new HealthPotion(), 0);
        this.inventory.addItemToInventory(new StaminaPotion(), 1);
        this.inventory.addItemToInventory(new StaminaPotion(), 2);
    }

    @Override
    public String getType() {
        return "Melee";
    }

    @Override
    public Image render() {
        String path = "./img/melee_" + getGender() + ".gif";
        return new Image(getClass().getResource(path).toString());
    }
}
