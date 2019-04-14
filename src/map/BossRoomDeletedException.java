package src.map;

public class BossRoomDeletedException extends Exception {
    private static final long serialVersionUID = 1L;

    public BossRoomDeletedException() {
        super("Boss room deleted during map generation");
    }
}