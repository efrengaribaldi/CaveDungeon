package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.weapon.*;
import src.item.potion.*;

public class Mage extends Player {
    public Mage(String name, int healthPoints, char gender, Inventory inventory, double attack, double defense) {
        super(name, healthPoints, gender, inventory, attack, defense);

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