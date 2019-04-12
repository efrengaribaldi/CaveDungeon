package src.map.gui;

import src.map.*;
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
        setupKeyboardControls();
        ImageView[] walls = m.getRoom(x, y).renderWalls();
        StackPane center = m.getRoom(x, y).renderCenter();
        BorderPane root = new BorderPane(center, walls[0], walls[1], walls[2], walls[3]);
        root.setStyle("-fx-background-color: #1C1117;");
        setRoot(root);
    }

    private void setupKeyboardControls() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean couldMove;
                switch (event.getCode()) {
                case W:
                case UP:
                    couldMove = m.getRoom(x, y).movePlayer(-1, 0);
                    break;
                case D:
                case RIGHT:
                    couldMove = m.getRoom(x, y).movePlayer(0, 1);
                    break;
                case S:
                case DOWN:
                    couldMove = m.getRoom(x, y).movePlayer(1, 0);
                    break;
                case A:
                case LEFT:
                    couldMove = m.getRoom(x, y).movePlayer(0, -1);
                    break;
                default:
                }
                BorderPane root = (BorderPane) getRoot();
                root.setCenter(m.getRoom(x, y).renderCenter());
            }
        });
    }
}