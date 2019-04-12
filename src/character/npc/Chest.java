package src.character.npc;

import src.character.NPC;
import src.character.Player;

import javafx.scene.image.ImageView;
import java.util.concurrent.ThreadLocalRandom;

public class Chest extends NPC {
    public Chest() {
        super("Chest", 0, 0, 0);
    }

    @Override
    public ImageView render() {
        return new ImageView(getClass().getResource("./chest/img/chest0.png").toString());
    }

    @Override
    public int getExperience() {
        return 0;
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
        return "Chest";
    }
}
