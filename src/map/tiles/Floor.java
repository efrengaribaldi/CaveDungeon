package src.map.tiles;

import src.character.npc.boss.Boss;
import src.character.npc.enemy.Enemy;
import src.item.chest.Chest;
import src.utils.Vector2D;

public class Floor extends src.map.Tile {
    private Enemy enemy;
    private Boss boss;
    private Chest chest;
    private Vector2D skullPos;

    public Floor() {
        super();
    }

    @Override
    public void addEnemy() {
        String nombre = "";
        int healthPoints = 0, experience = 0;
        // Set random propierties when generating the enemy
        enemy = new Enemy(nombre, healthPoints, experience);
    }

    @Override
    public boolean hasEnemy() {
        return enemy != null || boss != null;
    }

    @Override
    public void addBoss() {
        String nombre = "";
        int healthPoints = 0, experience = 0;
        // Set random propierties when generating the boss
        boss = new Boss(nombre, healthPoints, experience);
    }

    @Override
    public void addChest() {
        // Add random items when generating the chest
        chest = new Chest();
    }

    @Override
    public boolean hasChest() {
        return chest != null;
    }

    @Override
    public void addSkull(Vector2D pos) {
        skullPos = pos;
    }

    @Override
    public boolean hasSkull() {
        return skullPos != null;
    }
}