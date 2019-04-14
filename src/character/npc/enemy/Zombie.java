package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;

public class Zombie extends Enemy {
    public Zombie() {
        super("Zombie", 20, 4, 2);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(3, 4 + 1);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(12, 18);
        int baseStamina = ThreadLocalRandom.current().nextInt(2, 6);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    public String getType() {
        return "Zombie";
    }

    @Override
    public ImageView render() {
        String path = "./img/zombie.gif";
        return new ImageView(getClass().getResource(path).toString());
    }
}