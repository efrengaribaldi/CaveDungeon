package old.item.weapon;

import old.item.Weapon;
import old.character.Ability;

public class Bow extends Weapon {
    public Bow() {
        super("Knight's Bow");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Single Shot", 15);
        abilities[1] = new Ability("Triple Shot", 30);
        abilities[2] = new Ability("Critical Shot", 50);
        super.setAbilities(abilities);
    }
}
