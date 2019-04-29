package src.map;

import src.character.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Random random = new Random();
    private Room rooms[][];
    private int minNumRooms, sizeX, sizeY;
    public int startX, startY;
    // 0 to be defined, 1 initial, 2 easy, 3 hard, 4 treasure, 5 boss
    private final int[] probabilities = { 0, 0, 45, 90, 100, 100 };
    private final int doorChance = 30;

    public Map(long seed) {
        random.setSeed(seed);
        minNumRooms = 9;
        sizeX = 7;
        sizeY = 9;
        // Generate map layout
        generateMap();
        // Generate every room
        for (Room[] roomArr : rooms)
            for (Room r : roomArr)
                if (r != null)
                    r.generateRoom();
    }

    public Map() {
        
    }

    private void generateMap() {
        // Declare a counter of rooms created
        int roomCount;
        // Boolean to check if boss room has been deleted / not connected
        boolean hasBossRoom;
        do {
            // Declare array of rooms
            rooms = new Room[sizeX][sizeY];
            // Declare an arraylist to store coordinates of rooms to create
            ArrayList<int[]> roomsToCreate = new ArrayList<int[]>();
            // Set center room as initial positions
            startX = (int) Math.floor((sizeX - 1) / 2.0);
            startY = (int) Math.floor((sizeY - 1) / 2.0);
            declareRoom(startX, startY, 1, roomsToCreate);
            // Initialize counter of rooms created
            roomCount = 1;
            // Generate rooms with random probabilities
            int i = 0;
            for (; i < roomsToCreate.size() - 2; i++) {
                declareRoom(roomsToCreate.get(i)[0], roomsToCreate.get(i)[1], 0, roomsToCreate);
                roomCount++;
            }
            if (roomsToCreate.size() > 1) {
                // Generate boss room
                declareRoom(roomsToCreate.get(i)[0], roomsToCreate.get(i)[1], 5, roomsToCreate);
            }
            closeUnusedDoors();
            hasBossRoom = true;
            try {
                deleteUnconnectedRooms();
            } catch (BossRoomDeletedException e) {
                hasBossRoom = false;
            }
        } while (roomCount < minNumRooms || !hasBossRoom);
    }

    private void declareRoom(int x, int y, int state, ArrayList<int[]> roomsToCreate) {
        // Protect room array against overwrites
        if (rooms[x][y] != null) {
            // Only overwrite if it already has a state
            if (state != 0)
                rooms[x][y].setState(state);
            else
                return;
        }
        // 0 top, 1 right, 2 bottom, 3 left
        boolean[] doors = new boolean[4];
        // Generate random door arrangement
        for (int i = 0; i < 4; i++)
            doors[i] = getRandomRange(1, 100) <= doorChance;
        // Check if it is next to walls (but leave the entire border with null rooms)
        doors[0] = (x == 1) ? false : doors[3];
        doors[2] = (x == sizeX - 2) ? false : doors[1];
        doors[3] = (y == 1) ? false : doors[0];
        doors[1] = (y == sizeY - 2) ? false : doors[2];
        // Check if there are doors from neighboring rooms to this one
        // If there are, add them to this one, else don't
        if (rooms[x - 1][y] != null)
            doors[0] = rooms[x - 1][y].getDoor(2);
        if (rooms[x][y + 1] != null)
            doors[1] = rooms[x][y + 1].getDoor(3);
        if (rooms[x + 1][y] != null)
            doors[2] = rooms[x + 1][y].getDoor(0);
        if (rooms[x][y - 1] != null)
            doors[3] = rooms[x][y - 1].getDoor(1);
        // If there isn't a room in a direction, and there is a door
        // from this room to that direction, add a room there
        if (rooms[x - 1][y] == null && doors[0])
            roomsToCreate.add(new int[] { x - 1, y });
        if (rooms[x][y + 1] == null && doors[1])
            roomsToCreate.add(new int[] { x, y + 1 });
        if (rooms[x + 1][y] == null && doors[2])
            roomsToCreate.add(new int[] { x + 1, y });
        if (rooms[x][y - 1] == null && doors[3])
            roomsToCreate.add(new int[] { x, y - 1 });
        // Create a room, set type and doors, and add the new room to map
        state = (state == 0) ? getRandomType() : state;
        // If it is adjacent to the initial room, and its state is 3, make it 2
        if ((y == startY && (x == startX + 1 || x == startX - 1))
                || (x == startX && (y == startY - 1 || y == startY + 1)))
            state = (state == 3) ? 2 : state;
        Room r = new Room(state, doors);
        rooms[x][y] = r;
    }

    private void deleteUnconnectedRooms() throws BossRoomDeletedException {
        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                if (rooms[x][y] != null)
                    if (rooms[x][y].isNotConnected()) {
                        if (rooms[x][y].getState() == 5)
                            throw new BossRoomDeletedException();
                        else
                            rooms[x][y] = null;
                    }
    }

    private void closeUnusedDoors() {
        for (int x = 1; x < sizeX - 1; x++)
            for (int y = 1; y < sizeY - 1; y++)
                if (rooms[x][y] != null) {
                    if (rooms[x - 1][y] == null)
                        rooms[x][y].setDoor(0, false);
                    if (rooms[x][y + 1] == null)
                        rooms[x][y].setDoor(1, false);
                    if (rooms[x + 1][y] == null)
                        rooms[x][y].setDoor(2, false);
                    if (rooms[x][y - 1] == null)
                        rooms[x][y].setDoor(3, false);
                }
    }

    private int getRandomType() {
        int rNum = getRandomRange(1, 100);
        for (int i = 0; i < probabilities.length; i++)
            if (rNum < probabilities[i])
                return i;
        return 2;
    }

    private static int getRandomRange(int min, int max) {
        return random.nextInt(++max - min) + min;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    public void setPlayer(Player player) {
        rooms[startX][startY].setPlayer(player);
    }

    public String mapToString() {
        String res = "", whitespace = "";
        String[] roomSplit, row;
        // Find rooms to be trimmed from the left
        int minRooms = sizeY;
        for (int x = 1; x < sizeX - 1; x++) {
            int currentRooms = 0;
            while (rooms[x][currentRooms] == null && currentRooms < sizeY - 1)
                currentRooms++;
            if (currentRooms < minRooms)
                minRooms = currentRooms;
        }
        // Generate a placeholder for null rooms
        for (int i = 0; i <= Room.sizeY + 2; i++)
            whitespace += " ";
        int leadingWhitespace = --minRooms * whitespace.length();
        // Generate the string
        for (int x = 1; x < sizeX - 1; x++) {
            row = new String[Room.sizeX + 2];
            for (int i = 0; i < row.length; i++)
                row[i] = "";
            for (int y = 1; y < sizeY - 1; y++) {
                if (rooms[x][y] != null) {
                    roomSplit = rooms[x][y].roomToString().split("\n");
                    // Show the state in the map
                    roomSplit[0] = "--" + rooms[x][y].getState() + roomSplit[0].substring(3);
                    for (int i = 0; i < row.length; i++)
                        row[i] += roomSplit[i] + " ";
                } else
                    for (int i = 0; i < row.length; i++)
                        row[i] += whitespace;
            }
            // Remove leading and trailing whitespace
            for (int i = 0; i < row.length; i++)
                res += row[i].substring(leadingWhitespace).replaceFirst("\\s++$", "") + "\n";
        }
        // Remove empty lines
        return res.replaceAll("\n\\s*\n", "\n");
    }
}