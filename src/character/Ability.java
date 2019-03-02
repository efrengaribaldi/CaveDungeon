package src.character;

public class Ability {
    private String name;
    private int baseDamage;

    public Ability(String name, int baseDamage) {
        this.name = name;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}
