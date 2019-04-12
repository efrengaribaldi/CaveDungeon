package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;
import javafx.scene.image.Image;

public class Sword extends Weapon {
    // Names from https://torchlight.fandom.com/wiki/Swords_(T2)
    private final String[] names = { "Grim Flayer", "Longclaw", "Longfang", "Dragon's Tooth", "Mournblade", "Bloodbath",
            "Voidsaber", "Netherblade", "Elven Sword" };

    public Sword(int baseAttack, int baseStamina) {
        super("",
                new Ability[] { new Ability("Stinger", baseAttack, baseStamina),
                        new Ability("Sword Storm", 2 * baseAttack, 2 * baseStamina),
                        new Ability("Lightning Blade", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina)) });
        setName(names[(int) (Math.random() * names.length)]);
    }

    @Override
    public String getType() {
        return "Sword";
    }

    //"sword" + (Integer.toString((int)(Math.random() * 5) + 1)) + ".png"

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/sword1.png").toString());
    }
}
