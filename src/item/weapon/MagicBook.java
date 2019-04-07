package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;

public class MagicBook extends Weapon {
    public MagicBook(int baseAttack, int baseStamina) {
        super("Lycka Himlen", new Ability[] {new Ability("Thunder", baseAttack, baseStamina),
            new Ability("Dark Bane", 2 * baseAttack, 2 * baseStamina), new Ability("Apocalypse", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina))});
    }
}
