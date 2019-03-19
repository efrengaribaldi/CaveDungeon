package tests.map.tiles;

import tests.map.Tile;
import tests.character.npc.Enemy;
import tests.character.npc.Boss;
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
        enemy = new Enemy();
    }

    public boolean hasEnemy() {
        return enemy != null || boss != null;
    }

    public void addBoss() {
        skull = false;
        boss = new Boss("b");
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
