package src.map.tiles;

import src.map.Tile;

public class Door extends Tile {
    // 0 top/bottom, 1 right, 2 left
    private int side;
    private boolean closed;

    public Door(int side) {
        super();
        this.side = side;
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}