package src.map;

import src.character.Player;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;

public class Room {
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss, 6 store
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];
    private boolean areDoorsOpen;
    private Tile tiles[][];
    private int[] playerPos;
    static final int sizeX = 7, sizeY = 11;
    private final int centerX = (int) Math.floor((sizeX - 1) / 2.0);
    private final int centerY = (int) Math.floor((sizeY - 1) / 2.0);

    protected Room(int state, boolean[] doors) {
        this.state = state;
        this.doors = doors;
        this.areDoorsOpen = false;
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

    public void checkIfDoorsShouldOpen() {
        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                if (tiles[x][y].hasCharacter() == 'b' || tiles[x][y].hasCharacter() == 'e') {
                    this.areDoorsOpen = false;
                    return;
                }
        this.areDoorsOpen = true;
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
        ArrayList<int[]> possibleTiles = new ArrayList<>();
        for (int x = 0; x < sizeX - 2; x++)
            for (int y = 0; y < sizeY - 2; y++)
                possibleTiles.add(new int[] { x, y });
        int numEnemies = state + (int) (Math.random() * 3);
        // Choose numEnemies random tiles from possibleTiles
        ArrayList<int[]> chosenTiles = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) {
            int index = (int) (Math.random() * possibleTiles.size());
            chosenTiles.add(possibleTiles.get(index));
        }
        // Add enemies for every chosen tile
        for (int i = 0; i < numEnemies; i++)
            tiles[chosenTiles.get(i)[0]][chosenTiles.get(i)[1]].addEnemy(state);
    }

    protected void setPlayer(Player player, int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x < 0 || y < 0 || x >= sizeX || y >= sizeY)
            throw new ArrayIndexOutOfBoundsException();
        playerPos = new int[] { x, y };
        tiles[x][y].setPlayer(player);
    }

    protected void setPlayer(Player player) {
        setPlayer(player, centerX, centerY);
    }

    public boolean movePlayer(int x, int y) {
        Player p = (Player) tiles[playerPos[0]][playerPos[1]].getCharacter();
        try {
            int[] lastPlayerPos = playerPos;
            setPlayer(p, playerPos[0] + x, playerPos[1] + y);
            tiles[lastPlayerPos[0]][lastPlayerPos[1]].clearCharacter();
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            // To check if Player should move to another room
            if (playerPos[0] == centerX) {
                if (playerPos[1] == 0) {
                }
            }
            return false;
        }
    }

    String roomToString() {
        String res = "";
        for (int y = -1; y < sizeY + 1; y++)
            res += (doors[0] && y == centerY) ? "d" : "-";
        res += "\n";
        for (int x = 0; x < sizeX; x++) {
            res += (doors[3] && x == centerX) ? "d" : "|";
            for (int y = 0; y < sizeY; y++)
                res += tiles[x][y].hasCharacter();
            res += (doors[1] && x == centerX) ? "d" : "|";
            res += "\n";
        }
        for (int y = -1; y < sizeY + 1; y++)
            res += (doors[2] && y == centerY) ? "d" : "-";
        res += "\n";
        return res;
    }

    public ImageView[] renderWalls() {
        ImageView[] walls = new ImageView[4];
        for (int i = 0; i < walls.length; i++) {
            String doorState = doors[i] ? (areDoorsOpen ? "op" : "cl") : "no";
            String p1 = "./img/wall/" + i + "_" + doorState + ".png";
            walls[i] = new ImageView(getClass().getResource(p1).toString());
        }
        return walls;
    }

    public StackPane renderCenter() {
        GridPane floor = new GridPane();
        GridPane characters = new GridPane();
        for (int i = 0; i < sizeX; i++) {
            characters.getRowConstraints().add(new RowConstraints(64));
            for (int j = 0; j < sizeY; j++) {
                ImageView ivFloor = new ImageView(tiles[i][j].getSpritePath());
                ivFloor.setSmooth(false);
                ivFloor.setFitWidth(64);
                ivFloor.setFitHeight(64);
                ivFloor.setPreserveRatio(true);
                floor.add(ivFloor, j, i);

                ImageView ivChar;
                if (tiles[i][j].getCharacter() != null)
                    ivChar = tiles[i][j].getCharacter().render();
                else
                    ivChar = new ImageView(getClass().getResource("./img/emptyTile.png").toString());

                ivChar.setSmooth(false);
                if (tiles[i][j].hasCharacter() == 'b')
                    ivChar.setFitWidth(128);
                else
                    ivChar.setFitWidth(64);
                ivChar.setPreserveRatio(true);
                characters.add(ivChar, j, i);
                GridPane.setHalignment(ivChar, HPos.CENTER);
                GridPane.setValignment(ivChar, VPos.BOTTOM);
            }
        }
        StackPane stack = new StackPane(floor, characters);
        return stack;
    }
}