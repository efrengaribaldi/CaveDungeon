package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.weapon.*;
import src.item.potion.*;

public class Mage extends Player {
    public Mage(String name, char gender) {
        super(name, 25, gender, new Inventory(), 1.0, 1.2);
        this.inventory.addItemToInventory(new EnchantedBook(15, 5), 0);
        this.inventory.addItemToInventory(new Wand(15, 5), 1);
        this.inventory.addItemToInventory(new HealthPotion(15), 0);
        this.inventory.addItemToInventory(new StaminaPotion(10), 1);
    }

    @Override
    public char getType() {
        return 'a';
    }
}