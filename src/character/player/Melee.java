package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.weapon.*;
import src.item.potion.*;

public class Melee extends Player {
    public Melee(String name, char gender) {
        super(name, 20, gender, new Inventory(), 1.2, 1.0);
        this.inventory.addItemToInventory(new Sword(15, 5), 0);
        this.inventory.addItemToInventory(new Bow(15, 5), 1);
        this.inventory.addItemToInventory(new HealthPotion(15), 0);
        this.inventory.addItemToInventory(new StaminaPotion(10), 1);
    }

    @Override
    public char getType() {
        return 'e';
    }
}
