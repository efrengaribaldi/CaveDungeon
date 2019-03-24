package tests.item.weapon;

import tests.item.Weapon;
import tests.character.Ability;

public class Bow extends Weapon {
    public Bow() {
        super("Knight's Bow");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Single Shot", 15, 5);
        abilities[1] = new Ability("Triple Shot", 30, 10);
        abilities[2] = new Ability("Critical Shot", 50, 25);
        super.setAbilities(abilities);
    }
}
