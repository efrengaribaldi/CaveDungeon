package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.ImageView;

public class BigDemon extends Boss {
    public BigDemon() {
        super("BigDemon");
    }

    @Override
    public String getType() {
        return "BigDemon";
    }

    @Override
    public ImageView render() {
        String path = "./img/bigDemon.gif";
        return new ImageView(getClass().getResource(path).toString());
    }
}