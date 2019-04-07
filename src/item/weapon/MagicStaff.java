package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;

public class MagicStaff extends Weapon {
    public MagicStaff(int baseAttack, int baseStamina) {
        super("Magic Staff", new Ability[] {new Ability("Fire Attack", baseAttack, baseStamina),
            new Ability("Fire Attack II", 2 * baseAttack, 2 * baseStamina), new Ability("Fire Aspect", (int) (3.5 * baseAttack), (int) (3.5 *baseStamina))});
    }
}
