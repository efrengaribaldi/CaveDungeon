package src.game;

import src.map.*;

public class Game {
    private Map[] levels;

    public Game() {
        long gameSeed = System.currentTimeMillis();
        // long gameSeed = 1550428031406L;
        // For testing purposes
        System.out.println("Seed: " + gameSeed);
        levels = new Map[1];
        for (int i = 0; i < levels.length; ++i)
            levels[i] = new Map(gameSeed + i);
        // For testing purposes
        for (int i = 0; i < levels.length; ++i)
            System.out.println(levels[i].mapToString());
    }
}