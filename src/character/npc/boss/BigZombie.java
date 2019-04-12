package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.ImageView;

public class BigZombie extends Boss {
    public BigZombie() {
        super("BigZombie");
    }

    @Override
    public String getType() {
        return "BigZombie";
    }

    @Override
    public ImageView render() {
        String path = "./img/bigZombie1.png";
        return new ImageView(getClass().getResource(path).toString());
    }
}