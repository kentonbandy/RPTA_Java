package org.kentonbandy;

public class Armor extends Item {
    private int defense;

    /**
     * @param name
     * @param description
     * @param defense
     * @param price
     */
    public Armor(String name, String description, int price, int defense) {
        super(name, description, price);
        this.defense = defense;
    }

    /**
     * @param name
     * @param description
     * @param gettable
     * @param defense
     * @param price
     */
    public Armor(String name, String description, int price, boolean gettable, int defense) {
        super(name, description, price, gettable);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

}
