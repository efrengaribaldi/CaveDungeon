package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;

public class Chort extends Enemy {
    public Chort() {
        super("Chort", 40, 12, 6);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(5, 10);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(20, 26);
        int baseStamina = ThreadLocalRandom.current().nextInt(6, 10);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    protected int getDefenseForArmor(Player player) {
        return ThreadLocalRandom.current().nextInt(7, 9);
    }

    @Override
    public String getType() {
        return "Chort";
    }

    @Override
    public Image render() {
        String path = "./img/chort.gif";
        return new Image(getClass().getResource(path).toString());
    }
}