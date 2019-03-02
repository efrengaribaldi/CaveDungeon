package src.item.weapon.magicbook;

import src.item.weapon.Weapon;
import src.character.Ability;

public class MagicBook extends Weapon {
    public MagicBook() {
        super("Lycka Himlen");
        Ability[] abilities = new Ability[3];
        abilities[0] = new Ability("Thunder", 15);
        abilities[1] = new Ability("Dark Bane", 30);
        abilities[2] = new Ability("Apocalypse", 50);
        super.setAbilities(abilities);
    }


}
