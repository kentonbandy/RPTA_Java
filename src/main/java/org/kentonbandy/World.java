package org.kentonbandy;

import org.kentonbandy.DAO.DataLoader;
import org.kentonbandy.DAO.ObjectFactory;
import org.kentonbandy.action.Attack;
import org.kentonbandy.character.Enemy;
import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    private Map<String, Attack> attacks;
    private Map<String,Item> items;
    private Map<String,Armor> armors;
    private Map<String,Weapon> weapons;
    private Map<String,Item> aggregateItems;
    private Map<String,Enemy> enemies;
    private Map<String,ShopOwner> shopOwners;
    private Map<String,Location> locations;

    public void buildWorld(ObjectFactory factory) {
        attacks = factory.buildAttacksMap();
        items = factory.buildItemMap();
        armors = factory.buildArmorMap();
        weapons = factory.buildWeaponMap(attacks);
        buildAggregateItems();
        enemies = factory.buildEnemyMap(items, armors, weapons);
        locations = factory.buildLocationMap(aggregateItems, enemies, shopOwners);
    }

    private void buildAggregateItems() {
        aggregateItems = new HashMap<>();
        for (String key : items.keySet()) aggregateItems.put(key, items.get(key));
        for (String key : armors.keySet()) aggregateItems.put(key, armors.get(key));
        for (String key : weapons.keySet()) aggregateItems.put(key, weapons.get(key));
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }
}
