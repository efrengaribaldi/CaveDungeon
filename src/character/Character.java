package src.character;

public abstract class Character {
    private String nombre;
    private int healthPoints;

    public Character(String nombre, int healthPoints) {
        this.nombre = nombre;
        this.healthPoints = healthPoints;
    }

    public Character() {
        // Set random name, hp and experience
        // For boss and enemy npc
    }
}
