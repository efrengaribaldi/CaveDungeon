package src.item.weapon;

import src.item.Weapon;
import src.character.Ability;

public class MagicStaff extends Weapon {
    public MagicStaff() {
        super("Magic Staff");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Fire Attack", 15, 5);
        abilities[1] = new Ability("Fire Attack II", 30, 10);
        abilities[2] = new Ability("Fire Aspect", 50, 25);
        super.setAbilities(abilities);
    }
}
