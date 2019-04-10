package src.character.npc;

import src.character.NPC;

public class Boss extends NPC {
    public Boss(String name) {
        super(name, 1000, 20);
    }

    @Override
    public double getDefense() {
        return 15;
    }
}