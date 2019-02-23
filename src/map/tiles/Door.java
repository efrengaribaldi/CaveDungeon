package src.map.tiles;

public class Door extends src.map.Tile {
    // 0 top/bottom, 1 right, 2 left
    private int side;

    public Door(int side) {
        super();
        this.side = side;
    }

    @Override
    public int getSide() {
        return side;
    }
}