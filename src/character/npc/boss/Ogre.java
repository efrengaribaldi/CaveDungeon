package src.character.npc.boss;

import src.character.npc.Boss;

import javafx.scene.image.ImageView;

public class Ogre extends Boss {
    public Ogre() {
        super("Ogre");
    }

    @Override
    public String getType() {
        return "Ogre";
    }

    @Override
    public ImageView render() {
        String path = "./img/ogre.gif";
        return new ImageView(getClass().getResource(path).toString());
    }
}