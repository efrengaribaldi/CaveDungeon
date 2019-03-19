package tests.map;

import tests.map.Room;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class RoomGUI extends Application {
    private GridPane grid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        boolean[] doors = { true, true, true, true };
        Room room = new Room(1, doors);
        room.generateRoom();
        System.out.println(room.roomToString());
        grid = room.render();
        VBox root = new VBox(grid);
        Scene scene = new Scene(root);
        scene.setFill(Color.web("#222222"));
        stage.setScene(scene);
        stage.setTitle("Room");
        stage.show();
    }
}

// Node getChildAtRowCol(int row, int col) {
// for (Node child : grid.getChildren())
// if (GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) ==
// row)
// return child;
// return null;
// }