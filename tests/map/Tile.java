package tests.map;

public class Tile extends Object {
    public Tile() {

    }

    // Methods overriden by wall
    public int getSide() {
        return -1;
    }
}