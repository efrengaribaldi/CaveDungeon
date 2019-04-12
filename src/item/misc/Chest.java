package src.item.misc;

import src.item.Item;
import javafx.scene.image.Image;

public class Chest extends Item {
    public Chest() {
        super("Chest");
    }

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/chest_empty_0.png").toString());
    }
}
