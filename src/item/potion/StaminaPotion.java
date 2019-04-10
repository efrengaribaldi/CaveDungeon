package src.item.potion;

import src.item.Potion;

public class StaminaPotion extends Potion {
    public StaminaPotion(int recoveryPoints) {
        super("Stamina Potion", recoveryPoints);
    }

    @Override
    public char getType() {
        return 's';
    }
}
