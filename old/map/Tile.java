package old.map;

public class Tile {
    public Tile() {

    }

    // Methods overriden by wall
    public int getSide() {
        return -1;
    }
}