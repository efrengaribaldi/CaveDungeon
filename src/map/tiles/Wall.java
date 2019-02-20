package src.map.tiles;

public class Wall extends src.map.Tile {
    // 0 top/bottom, 1 right/left
    private int side;

    public Wall(int side) {
        super();
        this.side = side;
    }

    public int getSide() {
        return side;
    }
}