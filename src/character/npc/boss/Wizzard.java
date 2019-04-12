package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.ImageView;

public class Wizzard extends Boss {
    public Wizzard() {
        super("Wizzard");
    }

    @Override
    public String getType() {
        return "Wizzard";
    }

    @Override
    public ImageView render() {
        String path = "./img/wizzard1.png";
        return new ImageView(getClass().getResource(path).toString());
    }
}