package src.map.gui;

import src.map.Map;
import src.map.Room;
import src.character.Player;
import src.game.Game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MapRender extends Scene {
    private Game game;
    private Map m;
    private int x, y;
    
    public MapRender(Game game, Map map) {
        this(game, map, map.startX, map.startY);
    }

    public MapRender(Game game, Map map, int x, int y) {
        super(new BorderPane());
        this.game = game;
        this.m = map;
        this.x = x;
        this.y = y;
        setupKeyboardControls(this);
        m.getRoom(x, y).checkIfDoorsShouldOpen();
        ImageView[] walls = m.getRoom(x, y).renderWalls();
        StackPane center = m.getRoom(x, y).renderCenter();
        BorderPane root = new BorderPane(center, walls[0], walls[1], walls[2], walls[3]);
        root.setStyle("-fx-background-color: #1C1117;");
        setRoot(root);
    }

    private void setupKeyboardControls(MapRender mapRender) {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case W:
                case UP:
                    m.getRoom(x, y).movePlayer(-1, 0, mapRender);
                    break;
                case D:
                case RIGHT:
                    m.getRoom(x, y).movePlayer(0, 1, mapRender);
                    break;
                case S:
                case DOWN:
                    m.getRoom(x, y).movePlayer(1, 0, mapRender);
                    break;
                case A:
                case LEFT:
                    m.getRoom(x, y).movePlayer(0, -1, mapRender);
                    break;
                case E:
                    game.setInventoryScene();
                    break;
                default:
                }
                BorderPane root = (BorderPane) getRoot();
                root.setCenter(m.getRoom(x, y).renderCenter());
            }
        });
    }

    public void moveToRoom(String direction, Player player) {
        try {
            switch (direction) {
            case "UP":
                this.x--;
                m.getRoom(x, y).setPlayer(player, Room.sizeX - 1, Room.centerY);
                break;
            case "RIGHT":
                this.y++;
                m.getRoom(x, y).setPlayer(player, Room.centerX, 0);
                break;
            case "DOWN":
                this.x++;
                m.getRoom(x, y).setPlayer(player, 0, Room.centerY);
                break;
            case "LEFT":
                this.y--;
                m.getRoom(x, y).setPlayer(player, Room.centerX, Room.sizeY - 1);
                break;
            }
            m.getRoom(x, y).checkIfDoorsShouldOpen();
            ImageView[] walls = m.getRoom(x, y).renderWalls();
            StackPane center = m.getRoom(x, y).renderCenter();
            BorderPane root = new BorderPane(center, walls[0], walls[1], walls[2], walls[3]);
            root.setStyle("-fx-background-color: #1C1117;");
            setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }
}