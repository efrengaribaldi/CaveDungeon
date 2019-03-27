package src.item.weapon;

import src.item.Weapon;
import src.character.Ability;

public class Sword extends Weapon {
    public Sword() {
        super("Elven Sword");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Stinger", 15, 5);
        abilities[1] = new Ability("Sword Storm", 30, 10);
        abilities[2] = new Ability("Lightning Blade", 50, 25);
        super.setAbilities(abilities);
    }
}
