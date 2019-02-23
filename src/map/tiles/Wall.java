package src.map.tiles;

public class Wall extends src.map.Tile {
    // 0 top/bottom, 1 right, 2 left
    private int side;

    public Wall(int side) {
        super();
        this.side = side;
    }

    @Override
    public int getSide() {
        return side;
    }
}