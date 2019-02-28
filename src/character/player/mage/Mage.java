package src.character.player.mage;

import src.character.player.Player;
import src.item.Inventory;

public class Mage extends Player {
    public Mage(String nombre, int healthPoints, char sex, int experience, Inventory inventory) {
        super(nombre, healthPoints, sex, experience, inventory);
    }
}