package src.item;

import src.item.Item;

public class Armor extends Item {
    private int baseDefense;

    public Armor(String name, int baseDefense) {
        super(name);
        this.baseDefense = baseDefense;
    }
}
