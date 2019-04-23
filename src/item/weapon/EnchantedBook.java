package src.item.weapon;

import src.item.Weapon;
import src.item.weapon.Ability;
import javafx.scene.image.Image;

public class EnchantedBook extends Weapon {
    private final String[] names = { "Lycka Himlen", "Order of Blood", "Oath of Harm", "Moon's Secrets",
            "Curse of Doom", "Breath of Torment", "Spirits' Infusion" };

    public EnchantedBook(int baseAttack, int baseStamina) {
        super("",
                new Ability[] { new Ability("Thunder", baseAttack, baseStamina),
                        new Ability("Dark Bane", 2 * baseAttack, 2 * baseStamina),
                        new Ability("Life Drain", (int) (3.5 * baseAttack), (int) (3.5 * baseStamina)) });
        setName(names[(int) (Math.random() * names.length)]);
        sprite = (int) (Math.random() * 5);
    }

    @Override
    public String getType() {
        return "EnchantedBook";
    }

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/enchantedbook" + sprite + ".png").toString());
    }
}
