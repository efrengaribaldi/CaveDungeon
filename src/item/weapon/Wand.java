package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;
import javafx.scene.image.Image;

public class Wand extends Weapon {
    // Names from https://torchlight.fandom.com/wiki/Wands_(T2)
    private final String[] names = { "Goat Head Wand", "Timefork", "Fraxl's Star", "Blizzard", "The Banewand",
            "Nether Wand", "The Wyrdwand" };

    public Wand(int baseAttack, int baseStamina) {
        super("",
                new Ability[] { new Ability("Fire Attack", baseAttack, baseStamina),
                        new Ability("Shattering Strike", 2 * baseAttack, 2 * baseStamina),
                        new Ability("Apocalypse", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina)) });
        setName(names[(int) (Math.random() * names.length)]);
        sprite = (int) (Math.random() * 4);
    }

    @Override
    public String getType() {
        return "Wand";
    }

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/wand" + sprite + ".png").toString());
    }
}
