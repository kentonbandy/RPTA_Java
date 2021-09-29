package org.kentonbandy.action;

public class Attack {
    private String name;
    private int power;

    public Attack(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }
}
