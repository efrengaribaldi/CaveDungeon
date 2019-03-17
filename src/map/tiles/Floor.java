package src.map.tiles;

import src.map.Tile;
import src.character.npc.boss.Boss;
import src.character.npc.enemy.Enemy;
import src.character.npc.enemy.chort.Chort;
import src.character.npc.enemy.necromancer.Necromancer;
import src.character.npc.enemy.skeleton.Skeleton;
import src.character.npc.enemy.swampy.Swampy;
import src.character.npc.enemy.zombie.Zombie;
import src.item.chest.Chest;

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
        // Set random propierties when generating the enemy
        // Enemy need to be specified
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
        // Set random propierties when generating the boss
        boss = new Boss();
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
