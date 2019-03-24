package tests.map;

import tests.map.Tile;
import tests.character.npc.Enemy;
import tests.character.npc.enemy.*;
import tests.character.npc.Boss;
import tests.character.npc.boss.*;
import tests.item.misc.Chest;

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

    public void addEnemy() {
        int randomEnemy = (int) (Math.random() * 5);
        switch (randomEnemy) {
        case 0:
            enemy = new Chort();
            break;
        case 1:
            enemy = new Necromancer();
            break;
        case 2:
            enemy = new Skeleton();
            break;
        case 3:
            enemy = new Swampy();
            break;
        case 4:
            enemy = new Zombie();
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