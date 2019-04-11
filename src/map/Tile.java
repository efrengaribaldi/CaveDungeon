package src.map;

import src.map.Tile;
import src.character.npc.Enemy;
import src.character.npc.enemy.*;
import src.character.npc.Boss;
import src.character.npc.boss.*;
import src.item.misc.Chest;

public class Tile {
    private int spriteNum;
    private Enemy enemy;
    private Boss boss;
    private Chest chest;

    public Tile() {
        super();
        int rand = (int) (Math.random() * 20) - 13;
        spriteNum = (rand < 1) ? 1 : rand;
    }

    public void addEnemy(int state) {
        switch (state) {
        case 2:
            if (Math.random() < 0.5)
                enemy = new Zombie();
            else
                enemy = new Skeleton();
            break;
        case 3:
            int randomEnemy = (int) (Math.random() * 9);
            if (randomEnemy < 5)
                enemy = new Chort();
            else if (randomEnemy < 8)
                enemy = new Swampy();
            else
                enemy = new Necromancer();
            break;
        }
    }

    public boolean hasEnemy() {
        return enemy != null || boss != null;
    }

    public void addBoss() {
        int randomBoss = (int) (Math.random() * 4);
        switch (randomBoss) {
        case 0:
            boss = new BigDemon();
            break;
        case 1:
            boss = new BigZombie();
            break;
        case 2:
            boss = new Ogre();
            break;
        case 3:
            boss = new Wizzard();
            break;
        }
    }

    public void addChest() {
        // Add random items when generating the chest
        chest = new Chest();
    }

    public boolean hasChest() {
        return chest != null;
    }

    public String getSpritePath() {
        return "./../map/img/floor/" + spriteNum + ".png";
    }
}