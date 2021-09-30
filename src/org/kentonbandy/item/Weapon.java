package org.kentonbandy.item;

public class Weapon extends Item {
    private int power;

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     * @param power how much attack power this weapon adds to the player
     */
    public Weapon(String name, String description, int price, int power) {
        super(name, description, price);
        this.power = power;
    }

    /**
     * @param name the name of the weapon
     * @param description weapon description
     * @param price purchasing price
     * @param gettable whether the player can get this weapon
     * @param power how much attack power this weapon adds to the player
     */
    public Weapon(String name, String description, int price, boolean gettable, int power) {
        super(name, description, price, gettable);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
