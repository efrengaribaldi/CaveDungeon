package src.item.weapon;

import src.item.Item;

public abstract class Weapon extends Item {
    private int baseDamage;
    public boolean weaponState;

    public Weapon(String name, boolean weaponState) {
        super(name);
        weaponState = false;
    }

    public void setWeaponState(boolean weaponState) {
        this.weaponState = weaponState;
    }

    public boolean getWeaponState() {
        return weaponState;
    }
    public int getBaseDamage() {
        return baseDamage;
    }
}
