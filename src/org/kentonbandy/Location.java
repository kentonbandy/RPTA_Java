package org.kentonbandy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private List<String> descriptions = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Map<String,Location> map = new HashMap<>();

    public Location(String name, List<String> descriptions, List<Item> items, Map<String,Location> map) {
        this.name = name;
        this.descriptions = descriptions;
        this.items = items;
        this.map = map;
    }

    public Location travel(String direction) {
        return map.get(direction);
    }

    public String getName() {
        return name;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<Item> getItems() {
        return items;
    }
}
