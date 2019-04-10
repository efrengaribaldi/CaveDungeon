package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;

public class Wand extends Weapon {
    // Names from https://torchlight.fandom.com/wiki/Wands_(T2)
    private final String[] names = { "Goat Head Wand", "Timefork", "Fraxl's Star", "Blizzard", "The Banewand",
            "Netherrealm Wand", "The Wyrdwand" };
    private String type;

    public Wand(int baseAttack, int baseStamina) {
        super("",
                new Ability[] { new Ability("Fire Attack", baseAttack, baseStamina),
                        new Ability("Shattering Strike", 2 * baseAttack, 2 * baseStamina),
                        new Ability("Apocalypse", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina)) });
        setName(names[(int) (Math.random() * names.length)]);
        this.type = "Wand";
    }

    public String getType() {
        return type;
    }
}