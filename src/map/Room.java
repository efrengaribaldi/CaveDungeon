package src.map;

import src.map.tiles.*;

public class Room {
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss, 6 store
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];
    private Tile[][] tiles;
    private final int sizeX = 15, sizeY = 21;

    public Room(int state, boolean[] doors) {
        this.state = state;
        this.doors = doors;
        tiles = new Tile[sizeX][sizeY];
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setDoor(int place, boolean door) {
        doors[place] = door;
    }

    public boolean getDoor(int place) {
        return doors[place];
    }

    public boolean isNotConnected() {
        return !doors[0] && !doors[1] && !doors[2] && !doors[3];
    }

    public void generateRoom() {
        System.out.println("Generating room with state " + state + ".");
        int centerX = (int) Math.floor((sizeX - 1) / 2.0);
        int centerY = (int) Math.floor((sizeY - 1) / 2.0);
        // Create doors
        if (doors[0])
            tiles[centerX][0] = new Door(0);
        if (doors[1])
            tiles[sizeX - 1][centerY] = new Door(1);
        if (doors[2])
            tiles[centerX][sizeY - 1] = new Door(2);
        if (doors[3])
            tiles[0][centerY] = new Door(3);
        // Create walls
        for (Tile t : tiles[0]) // top
            if (t == null)
                t = new Wall(0);
        for (Tile t : tiles[sizeX - 1]) // bottom
            if (t == null)
                t = new Wall(0);
        for (int i = 0; i < sizeX; i++) {
            if (tiles[i][0] == null) // right
                tiles[i][0] = new Wall(1);
            if (tiles[i][sizeY - 1] == null) // left
                tiles[i][sizeY - 1] = new Wall(1);
        }
        // Spawn blocks
        // Fill with floor
        for (Tile[] tileArr : tiles)
            for (Tile t : tileArr)
                if (t == null)
                    t = new Floor();
        // Spawn enemies
    }

    public String roomToString() {
        String res = "";
        for (Tile[] tileArr : tiles) {
            for (Tile t : tileArr) {
                if (t instanceof src.map.tiles.Wall)
                    res += "w";
                else if (t instanceof src.map.tiles.Door)
                    res += "d";
                else if (t instanceof src.map.tiles.Floor)
                    res += ".";
                else
                    res += " ";
            }
            res += "\n";
        }
        return res;
    }
}