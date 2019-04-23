package src.item.armor;

import src.item.Armor;
import javafx.scene.image.Image;

public class Ring extends Armor {
    // Names from https://torchlight.fandom.com/wiki/Rings_(T2)
    private final String[] names = { "Railspike Ring", "The Gann Ring", "The Singing Fire", "The Watchweald Eye",
            "Necrotic Ring", "Sinistrix" };

    public Ring(int baseDefense) {
        super("", baseDefense);
        setName(names[(int) (Math.random() * names.length)]);
        sprite = (int) (Math.random() * 5);
    }

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/ring" + sprite + ".png").toString());
    }
}
