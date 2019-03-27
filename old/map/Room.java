package old.map;

import old.map.tiles.*;
import old.utils.Vector2D;

import java.util.ArrayList;

public class Room {
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss, 6 store
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];
    private Floor[][] floorTiles;
    static final int sizeX = 9, sizeY = 15;
    private final int centerX = (int) Math.floor((sizeX - 1) / 2.0);
    private final int centerY = (int) Math.floor((sizeY - 1) / 2.0);

    protected Room(int state, boolean[] doors) {
        this.state = state;
        this.doors = doors;
        floorTiles = new Floor[sizeX - 2][sizeY - 2];
    }

    void setState(int state) {
        this.state = state;
    }

    int getState() {
        return state;
    }

    void setDoor(int place, boolean door) {
        doors[place] = door;
    }

    boolean getDoor(int place) {
        return doors[place];
    }

    boolean isNotConnected() {
        return !doors[0] && !doors[1] && !doors[2] && !doors[3];
    }

    void generateRoom() {
        // state = 5; // <-- to test different room layouts
        for (int x = 0; x < sizeX - 2; x++)
            for (int y = 0; y < sizeY - 2; y++)
                floorTiles[x][y] = new Floor();
        if (state == 2 || state == 3)
            spawnEnemies();
        if (state == 4)
            floorTiles[centerX - 1][centerY - 1].addChest();
        if (state == 5)
            floorTiles[centerX - 1][centerY - 1].addBoss();
    }

    private void spawnEnemies() {
        ArrayList<Vector2D> possibleTiles = new ArrayList<>();
        for (int x = 0; x < sizeX - 2; x++)
            for (int y = 0; y < sizeY - 2; y++)
                if (!floorTiles[x][y].hasSkull())
                    possibleTiles.add(new Vector2D(x, y));
        int numEnemies = state + (int) (Math.random() * 3);
        // Choose numEnemies random tiles from possibleTiles
        ArrayList<Vector2D> chosenTiles = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) {
            int index = (int) (Math.random() * possibleTiles.size());
            chosenTiles.add(possibleTiles.get(index));
        }
        // Add enemies for every chosen tile
        for (int i = 0; i < numEnemies; i++)
            floorTiles[chosenTiles.get(i).x][chosenTiles.get(i).y].addEnemy();
    }

    String roomToString() {
        String res = "";
        for (int x = 0; x < sizeX; x++) {
            switch (x) {
            case 0:
                for (int y = 0; y < sizeY; y++)
                    res += (doors[0] && y == centerY) ? "d" : "-";
                res += "\n";
                break;
            case sizeX - 1:
                for (int y = 0; y < sizeY; y++)
                    res += (doors[2] && y == centerY) ? "d" : "-";
                res += "\n";
                break;
            default:
                res += (doors[3] && x == centerX) ? "d" : "|";
                for (int y = 0; y < sizeY - 2; y++) {
                    if (floorTiles[x - 1][y].hasEnemy())
                        res += "e";
                    else if (floorTiles[x - 1][y].hasChest())
                        res += "c";
                    else if (floorTiles[x - 1][y].hasSkull())
                        res += "s";
                    else
                        res += " ";
                }
                res += (doors[1] && x == centerX) ? "d" : "|";
                res += "\n";
                break;
            }
        }
        return res;
    }
}