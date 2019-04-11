package src.character;

public abstract class Character {
    private String name;
    private int healthPoints;

    public Character(String name, int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
    }

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
}
