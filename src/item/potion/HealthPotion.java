package src.item.potion;

import src.item.Potion;
import javafx.scene.image.Image;

public class HealthPotion extends Potion {
    public HealthPotion() {
        super("Health Potion");
    }

    @Override
    public char getType() {
        return 'h';
    }

    @Override
    public Image render() {
        return new Image(getClass().getResource("./img/healthpotion.png").toString());
    }
}
