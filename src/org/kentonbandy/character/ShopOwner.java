package org.kentonbandy.character;

import org.kentonbandy.item.Item;

import java.util.List;

public class ShopOwner extends Character {
    private int currency;
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
    public ShopOwner(String name, String description, List<Item> inventory, int currency, String greeting, String farewell) {
        super(name, description, inventory);
        this.currency = currency;
        this.greeting = greeting;
        this.farewell = farewell;
    }

    public boolean buy(Item item) {
        if (item.isGettable()) {
            int price = item.getPrice();
            // check to see if player has adequate currency
            // remove price from player's currency
            pay(price);
            // use this.transferItem() to give the item to the player
            return true;
        }
        return false;
    }

    /**
     * @param amount
     */
    public void pay(int amount) {
        currency += amount;
    }

    /**
     * @param amount
     * @return
     */
    public boolean take(int amount) {
        if (amount <= currency) {
            currency -= amount;
            return true;
        }
        return false;
    }

    /**
     * @return Shop Owner's currency amount
     */
    public int getCurrency() {
        return currency;
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
