package org.kentonbandy.item;

public class Weapon extends Item {
    private int power;

    /**
     * @param name
     * @param description
     * @param price
     * @param power
     */
    public Weapon(String name, String description, int price, int power) {
        super(name, description, price);
        this.power = power;
    }

    /**
     * @param name
     * @param description
     * @param price
     * @param gettable
     * @param power
     */
    public Weapon(String name, String description, int price, boolean gettable, int power) {
        super(name, description, price, gettable);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
