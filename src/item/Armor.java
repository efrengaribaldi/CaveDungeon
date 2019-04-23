package src.item;

import src.item.Item;

public abstract class Armor extends Item {
    private int baseDefense;
    protected int sprite;

    public Armor(String name, int baseDefense) {
        super(name);
        this.baseDefense = baseDefense;
    }

    @Override
    public String getParent() {
        return "Armor";
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public String printDefense() {
        return baseDefense + " defense";
    }
}
