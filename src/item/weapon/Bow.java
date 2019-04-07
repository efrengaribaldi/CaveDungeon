package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;

public class Bow extends Weapon {
    public Bow(int baseAttack, int baseStamina) {
        super("Knight's Bow", new Ability[] {new Ability("Single Shot", baseAttack, baseStamina),
            new Ability("Triple Shot", 2 * baseAttack, 2 * baseStamina), new Ability("Critical Shot", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina))});
    }
}
