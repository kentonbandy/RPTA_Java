package org.kentonbandy;

import org.kentonbandy.character.*;
import org.kentonbandy.item.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private List<Item> items;
    private List<Enemy> enemies;
    private ShopOwner shop;
    private boolean isSafe;
    private Map<String,Location> map = new HashMap<>();

    /**
     * @param name display name for the Location
     * @param description text that will display for this Location
     * @apiNote an empty items List and directions Map will be created upon instantiation
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        enemies = new ArrayList<>();
        isSafe = true;
        map = new HashMap<>();
    }

    public Location travel(String direction) {
        return map.get(direction);
    }

    /**
     * @param direction
     * @param location
     */
    public void addExit(String direction, Location location) {
        map.put(direction, location);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        if (items.contains(item)) items.remove(item);
    }

    /**
     * @param enemy
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public ShopOwner getShop() {
        return shop;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @param enemies
     */
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * @param shop
     */
    public void setShop(ShopOwner shop) {
        this.shop = shop;
    }

    public void setSafety(boolean isSafe) {
        this.isSafe = isSafe;
    }
}
