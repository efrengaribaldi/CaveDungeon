package tests.map.tiles;

import tests.map.Tile;

import javafx.scene.image.ImageView;

public class Door extends Tile {
    // 0 top/bottom, 1 right, 2 left
    private int side;
    private boolean closed;

    private final ImageView[] sprites = {
            new ImageView(this.getClass().getResource("imgDoor/doors_leaf_closed.png").toString()),
            new ImageView(this.getClass().getResource("imgDoor/doors_leaf_open.png").toString()),
            new ImageView(this.getClass().getResource("imgDoor/doors_frame_left.png").toString()),
            new ImageView(this.getClass().getResource("imgDoor/doors_frame_right.png").toString()),
            new ImageView(this.getClass().getResource("imgDoor/doors_frame_top.png").toString()) };

    public Door(int side) {
        super();
        this.side = side;
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public ImageView[][] render() {
        ImageView[][] res = new ImageView[3][3];
        // Center
        res[1][1] = (closed) ? sprites[0] : sprites[1];
        res[1][1].setRotate(90 * side);
        // Sides and top
        switch (side) {
        case 0:
            res[1][0] = sprites[2];
            res[1][2] = sprites[3];
            res[0][1] = sprites[4];
            break;
        case 1:
            res[0][1] = sprites[2];
            res[0][1].setRotate(90);
            res[2][1] = sprites[3];
            res[2][1].setRotate(90);
            res[1][2] = sprites[4];
            res[1][2].setRotate(90);
            break;
        case 2:
            res[1][0] = sprites[3];
            res[1][0].setRotate(180);
            res[1][2] = sprites[2];
            res[1][2].setRotate(180);
            res[2][1] = sprites[4];
            res[2][1].setRotate(180);
            break;
        case 3:
            res[0][1] = sprites[3];
            res[0][1].setRotate(270);
            res[2][1] = sprites[2];
            res[2][1].setRotate(270);
            res[1][0] = sprites[4];
            res[1][0].setRotate(270);
            break;
        default:
            break;
        }
        return res;
    }
}