package src.item.weapon;

import src.item.Weapon;
import src.character.Ability;

public class MagicBook extends Weapon {
    public MagicBook() {
        super("Lycka Himlen");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Thunder", 15, 5);
        abilities[1] = new Ability("Dark Bane", 30, 10);
        abilities[2] = new Ability("Apocalypse", 50, 25);
        super.setAbilities(abilities);
    }

}
