package src.character.player;

import src.character.Player;
import src.item.Inventory;
import src.item.weapon.*;
import src.item.potion.*;

public class Melee extends Player {
    public Melee(String name, int healthPoints, char gender, Inventory inventory, double attack) {
        super(name, healthPoints, gender, inventory, attack);
        this.inventory.addItemToInventory(new Sword(), 0);
        this.inventory.addItemToInventory(new Bow(), 1);
        this.inventory.addItemToInventory(new HealthPotion(15), 0);
        this.inventory.addItemToInventory(new StaminaPotion(10), 1);
    }
}

    
