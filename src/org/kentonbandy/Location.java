package org.kentonbandy;

import org.kentonbandy.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private List<Item> items;
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
        map = new HashMap<>();
    }

    public Location travel(String direction) {
        return map.get(direction);
    }

    public void addExit(String direction, Location location) {
        map.put(direction, location);
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

    public void setDescription(String description) {
        this.description = description;
    }
}
