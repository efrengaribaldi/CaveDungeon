package src.map;

import java.io.Serializable;

public class TileAlreadyOccupiedException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public TileAlreadyOccupiedException() {
        super("This tile is already occupied");
    }
}