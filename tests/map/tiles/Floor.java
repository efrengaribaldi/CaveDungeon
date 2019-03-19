package tests.map.tiles;

import tests.map.Tile;
import tests.character.npc.Enemy;
import tests.character.npc.enemy.*;
import tests.character.npc.Boss;
import tests.character.npc.boss.*;
import tests.item.misc.Chest;

public class Floor extends Tile {
    private Enemy enemy;
    private Boss boss;
    private Chest chest;
    private boolean skull;

    public Floor() {
        super();
        // 4 in a 1000 chance it as a skull
        skull = (Math.random() < 0.004);
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
        skull = false;
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

    public boolean hasSkull() {
        return skull;
    }
}
