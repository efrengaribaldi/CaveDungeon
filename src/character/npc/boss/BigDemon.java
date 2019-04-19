package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.Image;

public class BigDemon extends Boss {
    public BigDemon() {
        super("BigDemon");
    }

    @Override
    public String getType() {
        return "BigDemon";
    }

    @Override
    public Image render() {
        String path = "./img/bigDemon.gif";
        return new Image(getClass().getResource(path).toString());
    }
}