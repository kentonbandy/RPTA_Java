package org.kentonbandy.item;

import org.kentonbandy.action.Attack;

import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item {
    private List<Attack> attacks;

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     */
    public Weapon(String name, String description, int price, List<Attack> attacks) {
        super(name, description, price);
        this.attacks = attacks;
    }

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     * @param gettable whether the player can get this weapon
     */
    public Weapon(String name, String description, int price, List<Attack> attacks, boolean gettable) {
        super(name, description, price, gettable);
        this.attacks = attacks;
    }

    public List<Attack> getAttackList() {
        return attacks;
    }

    public int getPower(Attack attack) {
        return attack.getPower();
    }
}
