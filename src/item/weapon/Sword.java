package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;

public class Sword extends Weapon {
    public Sword(int baseAttack, int baseStamina) {
        super("Elven Sword", new Ability[] {new Ability("Stinger", baseAttack, baseStamina),
            new Ability("Sword Storm", 2 * baseAttack, 2 * baseStamina), new Ability("Lightning Blade", (int) (3.5 * baseAttack), (int)(3.5 * baseStamina))});
    }
}
