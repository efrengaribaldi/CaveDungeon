package src.map;

public class TileAlreadyOccupiedException extends Exception {
    private static final long serialVersionUID = 1L;

    public TileAlreadyOccupiedException() {
        super("This tile is already occupied");
    }
}