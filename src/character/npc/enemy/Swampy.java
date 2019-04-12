package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;

public class Swampy extends Enemy {
    public Swampy() {
        super("Swampy", 50, 16, 8);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(10, 15 + 1);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(24, 30);
        int baseStamina = ThreadLocalRandom.current().nextInt(8, 12);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    public String getType() {
        return "Swampy";
    }

    @Override
    public ImageView render() {
        String path = "./img/swampy1.png";
        return new ImageView(getClass().getResource(path).toString());
    }
}