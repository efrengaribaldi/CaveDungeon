package src.map;

import src.map.tiles.*;
import src.utils.Vector2D;

import java.util.ArrayList;

public class Room {
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss, 6 store
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];
    private Tile[][] tiles;
    private final int sizeX = 7, sizeY = 21;
    private final int centerX = (int) Math.floor((sizeX - 1) / 2.0);
    private final int centerY = (int) Math.floor((sizeY - 1) / 2.0);

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
        // state = 5; // <-- to test different room layouts
        createDoors();
        createWalls();
        // Fill with floor
        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                if (tiles[x][y] == null)
                    tiles[x][y] = new Floor();
        if (state == 2 || state == 3)
            spawnEnemies();
        if (state == 4)
            tiles[centerX][centerY].addChest();
        if (state == 5)
            tiles[centerX][centerY].addBoss();
        generateDecoration();
    }

    private void createDoors() {
        if (doors[0]) // top
            tiles[0][centerY] = new Door(0);
        if (doors[1]) // right
            tiles[centerX][sizeY - 1] = new Door(1);
        if (doors[2]) // bottom
            tiles[sizeX - 1][centerY] = new Door(0);
        if (doors[3]) // left
            tiles[centerX][0] = new Door(2);
    }

    private void createWalls() {
        for (int i = 0; i < sizeY; i++) {
            if (tiles[0][i] == null) // top
                tiles[0][i] = new Wall(0);
            if (tiles[sizeX - 1][i] == null) // bottom
                tiles[sizeX - 1][i] = new Wall(0);
        }
        for (int i = 0; i < sizeX; i++) {
            if (tiles[i][0] == null) // right
                tiles[i][0] = new Wall(1);
            if (tiles[i][sizeY - 1] == null) // left
                tiles[i][sizeY - 1] = new Wall(1);
        }
    }

    private void spawnEnemies() {
        ArrayList<Vector2D> possibleTiles = new ArrayList<>();
        // For loops aren't from 0 to size because we exclude doors and walls
        for (int x = 1; x < sizeX - 1; x++)
            for (int y = 1; y < sizeY - 1; y++) {
                // These ifs are to leave empty the center column and row
                if (x == centerX)
                    x++;
                if (y == centerY)
                    y++;
                possibleTiles.add(new Vector2D(x, y));
            }
        int numEnemies;
        if (state == 2)
            numEnemies = 3 + (int) (Math.random() * 3);
        else // state == 3
            numEnemies = 5 + (int) (Math.random() * 3);
        // Choose numEnemies random tiles from possibleTiles
        ArrayList<Vector2D> chosenTiles = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) {
            int index = (int) (Math.random() * possibleTiles.size());
            chosenTiles.add(possibleTiles.get(index));
        }
        // Add enemies for every chosen tile
        for (int i = 0; i < numEnemies; i++)
            tiles[chosenTiles.get(i).x][chosenTiles.get(i).y].addEnemy();
    }

    private void generateDecoration() {
        int rX, rY;
        boolean randomTileIsEmpty = false;
        do {
            rX = (int) (Math.random() * sizeX);
            rY = (int) (Math.random() * sizeY);
            if (tiles[rX][rY] instanceof Floor)
                if (!tiles[rX][rY].hasEnemy() && !tiles[rX][rY].hasChest()) {
                    Vector2D v = new Vector2D(rX, rY);
                    tiles[rX][rY].addSkull(v);
                    randomTileIsEmpty = true;
                }
        } while (!randomTileIsEmpty);
    }

    public String roomToString() {
        String res = "";
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (tiles[x][y] instanceof Wall) {
                    if (tiles[x][y].getSide() == 0)
                        res += "-";
                    else
                        res += "|";
                } else if (tiles[x][y] instanceof Door)
                    res += "d";
                else if (tiles[x][y] instanceof Floor) {
                    if (tiles[x][y].hasEnemy())
                        res += "e";
                    else if (tiles[x][y].hasChest())
                        res += "c";
                    else if (tiles[x][y].hasSkull())
                        res += "s";
                    else
                        res += " ";
                } else
                    res += "#";
            }
            res += "\n";
        }
        return res;
    }
}