package tests.map;

import tests.utils.Vector2D;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class Room {
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss, 6 store
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];
    private Tile tiles[][];
    static final int sizeX = 7, sizeY = 11;
    private final int centerX = (int) Math.floor((sizeX - 1) / 2.0);
    private final int centerY = (int) Math.floor((sizeY - 1) / 2.0);

    protected Room(int state, boolean[] doors) {
        this.state = state;
        this.doors = doors;
        tiles = new Tile[sizeX][sizeY];
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
        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                tiles[x][y] = new Tile();
        if (state == 2 || state == 3)
            spawnEnemies();
        if (state == 4)
            tiles[centerX - 1][centerY - 1].addChest();
        if (state == 5)
            tiles[centerX - 1][centerY - 1].addBoss();
    }

    private void spawnEnemies() {
        ArrayList<Vector2D> possibleTiles = new ArrayList<>();
        for (int x = 0; x < sizeX - 2; x++)
            for (int y = 0; y < sizeY - 2; y++)
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
            tiles[chosenTiles.get(i).x][chosenTiles.get(i).y].addEnemy();
    }

    String roomToString() {
        String res = "";
        for (int y = -1; y < sizeY + 1; y++)
            res += (doors[0] && y == centerY) ? "d" : "-";
        res += "\n";
        for (int x = 0; x < sizeX; x++) {
            res += (doors[3] && x == centerX) ? "d" : "|";
            for (int y = 0; y < sizeY; y++) {
                if (tiles[x][y].hasEnemy())
                    res += "e";
                else if (tiles[x][y].hasChest())
                    res += "c";
                else
                    res += " ";
            }
            res += (doors[1] && x == centerX) ? "d" : "|";
            res += "\n";
        }
        for (int y = -1; y < sizeY + 1; y++)
            res += (doors[2] && y == centerY) ? "d" : "-";
        res += "\n";
        return res;
    }

    public BorderPane render() {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ImageView iv = new ImageView(getClass().getResource(tiles[i][j].getSpritePath()).toString());
                iv.setSmooth(false);
                iv.setFitWidth(80);
                iv.setFitHeight(80);
                iv.setPreserveRatio(true);
                gridPane.add(iv, j + 1, i + 1);
            }
        }

        ImageView[] walls = new ImageView[4];
        for (int i = 0; i < 4; i++) {
            String p1 = "./img/wall/" + i + "-" + (doors[i] ? "cl" : "no") + ".png";
            walls[i] = new ImageView(getClass().getResource(p1).toString());
        }

        BorderPane root = new BorderPane(gridPane, walls[0], walls[1], walls[2], walls[3]);
        root.setStyle("-fx-background-color: #1C1117;");
        return root;
    }
}