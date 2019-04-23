package src.character.npc.enemy;

import src.character.npc.Enemy;
import src.character.Player;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;

public class MimicChest extends Enemy {
    public MimicChest() {
        super("Mimic Chest", 50, 16, 8);
    }

    @Override
    public int getExperience() {
        return ThreadLocalRandom.current().nextInt(15, 20);
    }

    @Override
    protected int[] getArgsForWeapon(Player player) {
        int baseAttack = ThreadLocalRandom.current().nextInt(30, 36);
        int baseStamina = ThreadLocalRandom.current().nextInt(10, 14);
        int[] res = { baseAttack, baseStamina };
        return res;
    }

    @Override
    protected int getDefenseForArmor(Player player) {
        return ThreadLocalRandom.current().nextInt(10, 12);
    }

    @Override
    public String getType() {
        return "MimicChest";
    }

    @Override
    public Image render() {
        String path = "./img/mimic.gif";
        return new Image(getClass().getResource(path).toString());
    }
}