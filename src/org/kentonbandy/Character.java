package org.kentonbandy;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String description;
    private List<Item> inventory = new ArrayList<>();

    public Character(String name, String description, List<Item> inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    public void getItem(Item item) {
        inventory.add(item);
    }

    public void transferItem(Item item, Character recipient) {
        recipient.getItem(item);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
