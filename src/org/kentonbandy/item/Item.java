package org.kentonbandy.item;

import org.kentonbandy.Sellable;

import java.util.ArrayList;
import java.util.List;

public class Item implements Sellable {
    private String name;
    private String description;
    private int price;
    private boolean gettable;

    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.gettable = true;
    }

    public Item(String name, String description, int price, boolean gettable) {
        this(name, description, price);
        this.gettable = gettable;
    }

    public boolean equals(Item item) {
        if (name.equals(item.getName())) return true;
        return false;
    }

    public boolean isCurrency() {
        return false;
    }

    public void makeGettable() {
        gettable = true;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGettable() {
        return gettable;
    }

    public void setName(String name) {
        this.name = name;
    }
}
