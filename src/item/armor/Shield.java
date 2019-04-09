package src.item.armor;

import src.item.Armor;

public class Shield extends Armor {
    // Names from https://torchlight.fandom.com/wiki/Shields_(T2)
    private final String[] names = { "Tribal Warshield", "Shelter's Disk", "Sentinel's Watch", "The Centerwing",
            "Core of Fate" };

    public Shield(int baseDefense) {
        super("", baseDefense);
        setName(names[(int) (Math.random() * names.length)]);
    }
}