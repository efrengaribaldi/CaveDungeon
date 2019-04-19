package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.Image;

public class BigZombie extends Boss {
    public BigZombie() {
        super("BigZombie");
    }

    @Override
    public String getType() {
        return "BigZombie";
    }

    @Override
    public Image render() {
        String path = "./img/bigZombie.gif";
        return new Image(getClass().getResource(path).toString());
    }
}