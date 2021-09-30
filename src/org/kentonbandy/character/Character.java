package org.kentonbandy.character;

import org.kentonbandy.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String description;
    private int currency;
    private List<Item> inventory = new ArrayList<>();

    public Character(String name, String description, int currency, List<Item> inventory) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.inventory = inventory;
    }

    public void getItem(Item item) {
        inventory.add(item);
    }

    public void giveItem(Item item, Character recipient) {
        recipient.getItem(item);
        inventory.remove(item);
    }

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

    public int getCurrency() {
        return currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
