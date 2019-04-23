package src.item;

import src.item.Item;
import src.item.weapon.Ability;

public abstract class Weapon extends Item {
    private Ability[] abilities;
    protected int sprite;

    public Weapon(String name, Ability[] abilities) {
        super(name);
        this.abilities = abilities;
    }

    @Override
    public String getParent() {
        return "Weapon";
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public void addAbilityToWeapon(Ability ability, int index) {
        abilities[index] = ability;
    }

    public void removeAbilityFromWeapon(int index) {
        abilities[index] = null;
    }

    public Ability getAbility(int index) {
        return abilities[index];
    }

    public String printAbilities() {
        String res = "";
        for (int i = 0; i < abilities.length; ++i)
            res += abilities[i].getName() + ":\n  " + abilities[i].getBaseDamage() + " dmg, "
                    + abilities[i].getStaminaCost() + " sta\n";
        return res;
    }

    public abstract String getType();
}
