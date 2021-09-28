package org.kentonbandy;

import java.util.ArrayList;
import java.util.List;

public class Item implements Sellable {
    private String name;
    private String description;
    private int price;
    private boolean gettable = true;

    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(String name, String description, int price, boolean gettable) {
        this(name, description, price);
        this.gettable = gettable;
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
}
