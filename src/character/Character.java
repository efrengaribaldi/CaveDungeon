package src.character;

import java.io.Serializable;

import javafx.scene.image.Image;

public abstract class Character implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int healthPoints;

    public Character(String name, int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
    }

    //Constructor for des-serialization
    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public abstract String getType();

    public abstract String getParent();

    public abstract Image render();
}
