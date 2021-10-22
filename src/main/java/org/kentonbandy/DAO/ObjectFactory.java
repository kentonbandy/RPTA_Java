package org.kentonbandy.DAO;

import org.kentonbandy.Location;
import org.kentonbandy.action.Attack;
import org.kentonbandy.character.Enemy;
import org.kentonbandy.character.ShopOwner;
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

    public Map<String,Item> buildItemMap() {
        List<Item> items = new ArrayList<>();
        Map<String,Item> itemMap = new HashMap<>();
        List<String> lines = gameData.get("item");
        for (String line : lines) {
            String[] arr = line.split("\\|");
            items.add(buildItem(arr));
        }
        for (Item item : items) {
            if (item != null) itemMap.put(item.getName(), item);
        }
        return itemMap;
    }

    public Map<String,Armor> buildArmorMap() {
        List<Armor> armors = new ArrayList<>();
        Map<String,Armor> armorMap = new HashMap<>();
        List<String> lines = gameData.get("armor");
        for (String line : lines) {
            String[] arr = line.split("\\|");
            armors.add(buildArmor(arr));
        }
        for (Armor armor : armors) {
            if (armor != null) armorMap.put(armor.getName(), armor);
        }
        return armorMap;
    }

    public Map<String,Weapon> buildWeaponMap(Map<String,Attack> attackMap) {
        List<Weapon> weapons = new ArrayList<>();
        Map<String,Weapon> weaponMap = new HashMap<>();
        List<String> lines = gameData.get("weapon");
        for (String line : lines) {
            String[] arr = line.split("\\|");
            weapons.add(buildWeapon(arr, attackMap));
        }
        for (Weapon weapon : weapons) {
            if (weapon != null) weaponMap.put(weapon.getName(), weapon);
        }
        return weaponMap;
    }

    public Map<String, Enemy> buildEnemyMap(Map<String,Item> worldItems, Map<String,Armor> worldArmors, Map<String,Weapon> worldWeapons) {
        Map<String,Enemy> enemyMap = new HashMap<>();
        List<String> lines = gameData.get("enemy");
        List<Enemy> enemies = new ArrayList<>();
        for (String line : lines) {
            String[] arr = line.split("\\|");
            enemies.add(buildEnemy(arr, worldItems, worldArmors, worldWeapons));
        }
        for (Enemy enemy : enemies) {
            if (enemy != null) enemyMap.put(enemy.getName(), enemy);
        }
        return enemyMap;
    }

    public Map<String, Location> buildLocationMap(Map<String,Item> worldItems, Map<String,Enemy> worldEnemies, Map<String, ShopOwner> shops) {
        List<Location> locations = new ArrayList<>();
        Map<String,Location> locationMap = new HashMap<>();
        List<String> lines = gameData.get("location");
        for (String line : lines) {
            String[] arr = line.split("\\|");
            locations.add(buildLocation(arr, worldItems, worldEnemies, shops));
        }
        for (Location loc : locations) {
            if (loc != null) locationMap.put(loc.getName(), loc);
        }
        return locationMap;
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

    private Enemy buildEnemy(String[] arr, Map<String,Item> worldItems, Map<String,Armor> worldArmors, Map<String,Weapon> worldWeapons) {
        Enemy enemy = null;
        if (arr.length == 7) {
            String[] itemStrings = arr[3].split(",");
            List<Item> items = new ArrayList<>();
            for (String s : itemStrings) {
                items.add(worldItems.get(s));
            }
            enemy = new Enemy(arr[0], arr[1], Integer.parseInt(arr[2]), items, Integer.parseInt(arr[4]),
                    worldArmors.get(arr[5]), worldWeapons.get(arr[6]));
        }
        return enemy;
    }

    private Location buildLocation(String[] arr, Map<String,Item> worldItems, Map<String,Enemy> worldEnemies, Map<String, ShopOwner> shops) {
        Location location = null;
        if (arr.length == 8) {
            List<Item> items = new ArrayList<>();
            List<Enemy> enemies = new ArrayList<>();
            ShopOwner shop = shops.get(arr[5]);
            Map<String,String> map = new HashMap<>();
            for (String s : arr[2].split(",")) items.add(worldItems.get(s));
            for (String s : arr[3].split(",")) enemies.add(worldEnemies.get(s));
            for (String s : arr[7].split(",")) {
                String[] keyVal = s.split(":");
                map.put(keyVal[0], keyVal[1]);
            }
            location = new Location(arr[0], arr[1], items, enemies, shop, Boolean.parseBoolean(arr[6]), map);
        }
        return location;
    }
}