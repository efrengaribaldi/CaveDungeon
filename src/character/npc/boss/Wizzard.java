package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.Image;

public class Wizzard extends Boss {
    public Wizzard() {
        super("Wizzard");
    }

    @Override
    public String getType() {
        return "Wizzard";
    }

    @Override
    public Image render() {
        String path = "./img/wizzard.gif";
        return new Image(getClass().getResource(path).toString());
    }
}