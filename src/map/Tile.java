package src.map;

import src.map.Tile;
import src.character.Character;
import src.character.Player;
import src.character.npc.Chest;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.character.npc.boss.*;

public class Tile {
    private int spriteNum;
    private Character character;

    public Tile() {
        super();
        int rand = (int) (Math.random() * 20) - 13;
        spriteNum = (rand < 1) ? 1 : rand;
    }

    public void addEnemy(int state) {
        switch (state) {
        case 2:
            character = (Math.random() < 0.5) ? new Zombie() : new Skeleton();
            break;
        case 3:
            int randomEnemy = (int) (Math.random() * 9);
            if (randomEnemy < 5)
                character = new Chort();
            else if (randomEnemy < 8)
                character = new Swampy();
            else
                character = new Necromancer();
            break;
        }
    }

    public char hasCharacter() {
        if (character == null)
            return ' ';
        else {
            if (character instanceof Player)
                return 'p';
            else if (character instanceof Chest)
                return 'c';
            else if (character instanceof Enemy)
                return 'e';
            else // character instanceof Boss
                return 'b';
        }
    }

    public void addBoss() {
        int randomBoss = (int) (Math.random() * 4);
        switch (randomBoss) {
        case 0:
            character = new BigDemon();
            break;
        case 1:
            character = new BigZombie();
            break;
        case 2:
            character = new Ogre();
            break;
        case 3:
            character = new Wizzard();
            break;
        }
    }

    public void setPlayer(Player player) {
        this.character = player;
    }

    public Character getCharacter() {
        return character;
    }

    public void clearCharacter() {
        character = null;
    }

    public void addChest() {
        character = new Chest();
    }

    public String getSpritePath() {
        String path = "./img/floor/" + spriteNum + ".png";
        return getClass().getResource(path).toString();
    }
}