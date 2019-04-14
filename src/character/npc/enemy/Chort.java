package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;

public class Chort extends Enemy {
    public Chort() {
        super("Chort", 40, 12, 6);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(5, 10 + 1);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(20, 26);
        int baseStamina = ThreadLocalRandom.current().nextInt(6, 10);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    public String getType() {
        return "Chort";
    }

    @Override
    public ImageView render() {
        String path = "./img/chort.gif";
        return new ImageView(getClass().getResource(path).toString());
    }
}