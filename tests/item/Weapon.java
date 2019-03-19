package tests.item;

import tests.item.Item;
import tests.character.Ability;

public abstract class Weapon extends Item {
    public boolean weaponState;
    private Ability[] abilities;

    public Weapon(String name) {
        super(name);
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
                    ? "Name: " + abilities[i].getName() + " | HP Cost: " + abilities[i].getBaseDamage()
                    : "null";
            res += "\n";
        }
        res += "\n";
        return res;
    }
}
