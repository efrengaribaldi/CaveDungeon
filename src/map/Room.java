package src.map;

public class Room {
    // 0 - to be defined, 1 - initial, 2 - easy, 3 - hard, 4 - treasure, 5 - boss
    private int state;
    // 0 top, 1 right, 2 bottom, 3 left
    private boolean doors[];

    public Room(int state, boolean[] doors) {
        this.state = state;
        this.doors = doors;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setDoor(int place, boolean door) {
        doors[place] = door;
    }

    public boolean getDoor(int place) {
        return doors[place];
    }

    public boolean isNotConnected() {
        return !doors[0] && !doors[1] && !doors[2] && !doors[3];
    }
}