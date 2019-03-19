package tests.item.potion;

import tests.item.Potion;

public class HealthPotion extends Potion {
    private int recoveredHealth;

    public HealthPotion() {
        super("Health Potion");
        recoveredHealth = 10;
    }
}