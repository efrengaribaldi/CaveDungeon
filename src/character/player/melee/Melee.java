package src.character.player.melee;

import src.character.player.Player;
import src.item.Inventory;

public class Melee extends Player {
    public Melee(String nombre, int healthPoints, String sex, int experience, Inventory inventory) {
        super(nombre, healthPoints, sex, experience, inventory);
    }
}
