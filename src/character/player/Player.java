package src.character.player;

import src.character.Character;
import src.character.Ability;
import src.item.Inventory;

public abstract class Player extends Character implements IPlayer {
    public Inventory inventory;
    private int experience;
    private char sex;
    private Ability[] abilities = new Ability[3];

    public Player(String nombre, int healthPoints, char sex, int experience, Inventory inventory) {
        super(nombre, healthPoints);
        this.sex = sex;
        this.inventory = inventory;
        this.experience = experience;
    }

    /*
     * public abstract void useAbility(); public abstract void attackNPC(enemy :
     * NPC);
     * 
     * public void useItem() {
     * 
     * }
     */
}