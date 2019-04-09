package src.item.armor;

import src.item.Armor;

public class Ring extends Armor {
    // Names from https://torchlight.fandom.com/wiki/Rings_(T2)
    private final String[] names = { "Railspike Ring", "The Gann Ring", "The Singing Fire", "The Watchweald Eye",
            "Necrotic Ring", "Sinistrix" };

    public Ring(int baseDefense) {
        super("", baseDefense);
        setName(names[(int) (Math.random() * names.length)]);
    }
}