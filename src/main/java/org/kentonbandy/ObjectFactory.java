package org.kentonbandy;

import org.kentonbandy.action.Attack;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.*;

public class ObjectFactory {
    Map<String,List<String>> gameData;

    public ObjectFactory() {
        gameData = new HashMap<>();
    }

    public ObjectFactory(Map<String,List<String>> gameData) {
        this.gameData = gameData;
    }

    public Map<String,Attack> buildAttacksMap() {
        List<String> lines = gameData.get("attack");
        List<Attack> attacks = new ArrayList<>();
        for (String line : lines) {
            attacks.add(buildAttack(line));
        }
        Map<String,Attack> map = new HashMap<>();
        for (Attack attack : attacks) {
            if (attack == null) continue;
            map.put(attack.getName(), attack);
        }
        return map;
    }

    private Attack buildAttack(String line) {
        String[] arr = line.split("\\|");
        Attack attack;
        if (arr.length == 3) {
            attack = new Attack(arr[0], arr[1], Integer.parseInt(arr[2]));
        } else if (arr.length == 4) {
            attack = new Attack(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
        } else return null;
        return attack;
    }

    public Map<String,Item> buildItemsMap(Map<String, Attack> attacksMap) {
        // This list is populated with items, armor, and weapons!
        List<Item> items = new ArrayList<>();
        String[] keys = {"item", "armor", "weapon"};
        for (String key : keys) {
            List<String> lines = gameData.get(key);
            for (String line : lines) {
                String[] arr = line.split("\\|");
                int len = arr.length;
                if (len < 3 || len > 5) continue;
                if (key.equals("item")) {
                    if (len > 4) continue;
                    else items.add(buildItem(arr));
                } else if (len < 4) continue;
                else if (key.equals("armor")) items.add(buildArmor(arr));
                else items.add(buildWeapon(arr, attacksMap));
            }
        }
        Map<String,Item> map = new HashMap<>();
        for (Item item : items) {
            if (item == null) continue;
            map.put(item.getName(), item);
        }
        return map;
    }

    private Item buildItem(String[] arr) {
        Item item;
        if (arr.length == 3) {
            item = new Item(arr[0], arr[1], Integer.parseInt(arr[2]));
        } else if (arr.length == 4) {
            item = new Item(arr[0], arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]));
        } else return null;
        return item;
    }

    private Armor buildArmor(String[] arr) {
        Armor armor;
        if (arr.length == 4) {
            armor = new Armor(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
        } else if (arr.length == 5) {
            armor = new Armor(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Boolean.parseBoolean(arr[4]));
        } else return null;
        return armor;
    }

    private Weapon buildWeapon(String[] arr, Map<String,Attack> attackMap) {
        Weapon weapon;
        List<Attack> attacks = new ArrayList<>();
        if (arr.length >= 4 && arr.length < 6) {
            for (String key : arr[3].split(",")) {
                attacks.add(attackMap.get(key));
            }
        } else return null;
        if (arr.length == 4) {
            weapon = new Weapon(arr[0], arr[1], Integer.parseInt(arr[2]), attacks);
        } else {
            weapon = new Weapon(arr[0], arr[1], Integer.parseInt(arr[2]), attacks, Boolean.parseBoolean(arr[4]));
        }
        return weapon;
    }
}
