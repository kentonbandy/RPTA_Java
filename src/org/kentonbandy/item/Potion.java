package org.kentonbandy.item;

public class Potion extends Item {
    private int strength;
    private boolean healsHp;

    public Potion(String name, String description, int price, int strength, boolean healsHp) {
        super(name, description, price);
        this.strength = strength;
        this.healsHp = healsHp;
    }

    public int getStrength() {
        return strength;
    }

    public boolean healsHp() {
        return healsHp;
    }
}
