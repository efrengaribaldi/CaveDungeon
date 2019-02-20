package src.map.tiles;

public class Door extends src.map.Tile {
    // 0 top, 1 right, 2 bottom, 3 left
    private int side;

    public Door(int side) {
        super();
        this.side = side;
    }

    public int getSide() {
        return side;
    }
}