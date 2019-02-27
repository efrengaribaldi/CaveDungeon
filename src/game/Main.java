package src.game;

import src.item.*;

public class Main {
    public static void main(String[] args) {
        Game newGame = new Game();
        Inventory newInventory = new Inventory();
        System.out.println(newInventory.inventoryToString());
    }
}
