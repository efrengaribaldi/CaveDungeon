package src.map;

import src.utils.Vector2D;

public class Tile {

    public Tile() {

    }

    // Methods overriden by wall
    public int getSide() {
        return -1;
    }

    // Methods overriden by Floor
    public void addEnemy() {
    }

    public boolean hasEnemy() {
        return false;
    }

    public void addBoss() {
    }

    public void addChest() {
    }

    public boolean hasChest() {
        return false;
    }

    public void addSkull(Vector2D pos) {
    }

    public boolean hasSkull() {
        return false;
    }
}