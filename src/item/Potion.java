package src.item;

import src.item.Item;

public abstract class Potion extends Item {
    public Potion(String name) {
        super(name);
    }

    @Override
    public String getParent() {
        return "Potion";
    }

    public abstract char getType();
}
