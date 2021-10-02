package org.kentonbandy.item;

import org.kentonbandy.action.Attack;

public class Weapon extends Item {
    private Attack attack;

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     */
    public Weapon(String name, String description, int price, Attack attack) {
        super(name, description, price);
        this.attack = attack;
    }

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     * @param gettable whether the player can get this weapon
     */
    public Weapon(String name, String description, int price, Attack attack, boolean gettable) {
        super(name, description, price, gettable);
        this.attack = attack;
    }

    public Attack getAttack() {
        return attack;
    }

    public int getPower() {
        return attack.getPower();
    }
}
