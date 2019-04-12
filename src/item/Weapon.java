package src.item;

import src.item.Item;
import src.item.weapon.Ability;

public abstract class Weapon extends Item {
    public boolean weaponState;
    private Ability[] abilities;

    public Weapon(String name, Ability[] abilities) {
        super(name);
        this.abilities = abilities;
        this.weaponState = false;
    }

    public boolean getWeaponState() {
        return weaponState;
    }

    public void setWeaponState(boolean weaponState) {
        this.weaponState = weaponState;
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
        for (int i = 0; i < abilities.length; ++i) {
            res += "Ability[" + i + "] ";
            res += (abilities[i] != null)
                    ? "Name: " + abilities[i].getName() + " | Damage: " + abilities[i].getBaseDamage()
                            + " | Stamina cost: " + abilities[i].getStaminaCost()
                    : "null";
            res += "\n";
        }
        res += "\n";
        return res;
    }

    public abstract String getType();
}
