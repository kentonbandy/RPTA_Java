package org.kentonbandy.action;

public class Attack {
    private String name;
    private String description;
    private int power;
    private int mpCost;


    /**
     * @param name
     * @param description
     * @param power
     */
    public Attack(String name, String description, int power) {
        this.name = name;
        this.description = description;
        this.power = power;
        mpCost = 0;
    }

    public Attack(String name, String description, int power, int mpCost) {
        this(name, description, power);
        this.mpCost = mpCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPower() {
        return power;
    }

    public int getMpCost() {
        return mpCost;
    }
}
