package src.character.player.melee;

import src.character.player.Player;
import src.item.Inventory;

public class Melee extends Player {
    public Melee(String name, int healthPoints, char gender, int experience, Inventory inventory) {
        super(name, healthPoints, gender, experience, inventory);
    }
}
