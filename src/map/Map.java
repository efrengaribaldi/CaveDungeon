package src.map;

import src.utils.*;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private static Random random = new Random();
    private Room rooms[][];
    private int minNumRooms, sizeX, sizeY;
    // 0 - to be defined, 1 - initial, 2 - easy, 3 - hard, 4 - treasure, 5 - boss
    private final int[] probabilities = { 0, 0, 70, 90, 100, 100 };
    private final int doorChance = 30;

    public Map(long seed) {
        random.setSeed(seed);
        minNumRooms = 10;
        sizeX = 9;
        sizeY = 13;
        generateMap();
        // TO DO: generate every room
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
            ArrayList<Vector2D> roomsToCreate = new ArrayList<Vector2D>();
            // Set center room as initial positions
            int startX = (int) Math.floor((sizeX - 1) / 2.0);
            int startY = (int) Math.floor((sizeY - 1) / 2.0);
            generateRoom(startX, startY, 1, roomsToCreate);
            // Initialize counter of rooms created
            roomCount = 1;
            // Generate rooms with random probabilities
            int i = 0;
            for (; i < roomsToCreate.size() - 1; i++) {
                generateRoom(roomsToCreate.get(i).x, roomsToCreate.get(i).y, 0, roomsToCreate);
                roomCount++;
            }
            // Generate boss room
            if (roomsToCreate.size() > 1)
                generateRoom(roomsToCreate.get(i).x, roomsToCreate.get(i).y, 5, roomsToCreate);
            closeUnusedDoors();
            hasBossRoom = deleteUnconnectedRooms();
        } while (roomCount < minNumRooms || !hasBossRoom);
    }

    private void generateRoom(int x, int y, int state, ArrayList<Vector2D> roomsToCreate) {
        // Protect room array against overwrites
        if (rooms[x][y] != null) {
            // Only overwrite if it's a boss room
            if (state == 5)
                rooms[x][y].setState(5);
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
            roomsToCreate.add(new Vector2D(x - 1, y));
        if (rooms[x][y + 1] == null && doors[1])
            roomsToCreate.add(new Vector2D(x, y + 1));
        if (rooms[x + 1][y] == null && doors[2])
            roomsToCreate.add(new Vector2D(x + 1, y));
        if (rooms[x][y - 1] == null && doors[3])
            roomsToCreate.add(new Vector2D(x, y - 1));
        // Create a room, set type and doors, and add the new room to map
        state = (state == 0) ? getRandomType() : state;
        Room r = new Room(state, doors);
        rooms[x][y] = r;
    }

    private boolean deleteUnconnectedRooms() {
        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                if (rooms[x][y] != null)
                    if (rooms[x][y].isNotConnected()) {
                        if (rooms[x][y].getState() == 5)
                            return false;
                        else
                            rooms[x][y] = null;
                    }
        return true;
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

    public String mapToString() {
        String res = "", r1, r2, r3;
        for (Room[] roomArr : rooms) {
            r1 = "";
            r2 = "";
            r3 = "";
            for (Room room : roomArr) {
                if (room != null) {
                    r1 += " " + (room.getDoor(0) ? "|" : " ") + "  ";
                    r2 += (room.getDoor(3) ? "-" : " ") + room.getState() + (room.getDoor(1) ? "-" : " ") + " ";
                    r3 += " " + (room.getDoor(2) ? "|" : " ") + "  ";
                } else {
                    r1 += "    ";
                    r2 += " *  ";
                    r3 += "    ";
                }
            }
            res += r1 + "\n" + r2 + "\n" + r3 + "\n";
        }
        return res;
    }
}