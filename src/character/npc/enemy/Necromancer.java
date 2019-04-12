package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;

public class Necromancer extends Enemy {
    public Necromancer() {
        super("Necromancer", 80, 20, 10);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(15, 20 + 1);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(30, 36);
        int baseStamina = ThreadLocalRandom.current().nextInt(10, 14);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    public String getType() {
        return "Necromancer";
    }

    @Override
    public ImageView render() {
        String path = "./img/necromancer1.png";
        return new ImageView(getClass().getResource(path).toString());
    }
}