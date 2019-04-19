package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.Image;

public class Ogre extends Boss {
    public Ogre() {
        super("Ogre");
    }

    @Override
    public String getType() {
        return "Ogre";
    }

    @Override
    public Image render() {
        String path = "./img/ogre.gif";
        return new Image(getClass().getResource(path).toString());
    }
}