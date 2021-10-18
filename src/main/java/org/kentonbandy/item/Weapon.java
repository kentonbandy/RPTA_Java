package org.kentonbandy.item;

import org.kentonbandy.action.Attack;

import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item {
    private List<Attack> attacks;


    /**
     * @param name
     * @param description
     * @param price
     * @param attacks
     */
    public Weapon(String name, String description, int price, List<Attack> attacks) {
        super(name, description, price);
        this.attacks = attacks;
    }


    /**
     * @param name
     * @param description
     * @param price
     * @param attacks
     * @param gettable
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
