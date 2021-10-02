package org.kentonbandy.character;

import org.kentonbandy.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String description;
    private int currency;
    private List<Item> inventory = new ArrayList<>();

    /**
     * @param name
     * @param description
     * @param currency
     * @param inventory
     */
    public Character(String name, String description, int currency, List<Item> inventory) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.inventory = inventory;
    }

    /**
     * @param item adds the given item to the character's inventory
     */
    public void getItem(Item item) {
        inventory.add(item);
    }

    /**
     * @param item removes the given item from the character's inventory
     */
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getCurrencyAmount() {
        return currency;
    }

    /**
     * @param currency adds the given currency to the character's total currency
     */
    public void acquireCurrency(int currency) {
        this.currency += currency;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param currency
     */
    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
