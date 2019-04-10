package src.item;

import src.item.Item;

public abstract class Potion extends Item {
    private int recoveryPoints;

    public Potion(String name, int recoveryPoints) {
        super(name);
        this.recoveryPoints = recoveryPoints;
    }

    public int getRecoveryPoints() {
        return recoveryPoints;
    }

    public abstract char getType();
}
