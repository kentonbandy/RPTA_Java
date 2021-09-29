package org.kentonbandy.character;

import org.kentonbandy.item.Item;

import java.util.List;

public class ShopOwner extends Character {
    private String greeting;
    private String farewell;

    /**
     * @param name
     * @param description
     * @param inventory
     * @param currency
     * @param greeting
     * @param farewell
     */
    public ShopOwner(String name, String description, int currency, List<Item> inventory, String greeting, String farewell) {
        super(name, description, currency, inventory);
        this.greeting = greeting;
        this.farewell = farewell;
    }

    /**
     * @return Shop Owner's greeting line
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * @return Shop Owner's farewell line
     */
    public String getFarewell() {
        return farewell;
    }
}
