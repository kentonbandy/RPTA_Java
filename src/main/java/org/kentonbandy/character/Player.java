package org.kentonbandy.character;

import org.kentonbandy.UI;
import org.kentonbandy.action.Attack;
import org.kentonbandy.item.*;
import java.util.List;

public class Player {
    private static String name;
    private static String description;
    private static int currency;
    private static List<Item> inventory;
    private static int level;
    private static Armor armor;
    private static Weapon weapon;
    private static int hp;
    private static int mp;
    private static int xp;

    public static void setFields(String playerName, String playerDescription, int playerCurrency,
                                 List<Item> playerInventory, int playerLevel, Armor playerArmor, Weapon playerWeapon) {
        name = playerName;
        description = playerDescription;
        currency = playerCurrency;
        inventory = playerInventory;
        setLevel(playerLevel, true);
        armor = playerArmor;
        weapon = playerWeapon;
    }

    /**
     * Sets new level, Hp, and Mp values, all are set to full value
     */
    public static void levelUp() {
        setLevel(getLevel() + 1, false);
        UI.levelUp();
    }

    /**
     * @param level
     * @return Max HP value for the given level
     */
    public static int calculateHp(int level) {
        return 10 + (level * level);
    }


    /**
     * @param playerLevel the target level
     * @param resetXp Whether the XP should be set to the base amount for the target level
     */
    public static void setLevel(int playerLevel, boolean resetXp) {
        level = playerLevel;
        setHp(calculateHp(getLevel()));
        setMp(calculateMp(getLevel()));
        if (resetXp) setXp(calculateXpOfThisLevel());
    }

    /**
     * @param level
     * @return Max MP value for the given level
     */
    public static int calculateMp(int level) {
        return 8 * level;
    }

    public static int calculateXpToNextLevel() {
        return level * level * 5;
    }

    public static int calculateXpOfThisLevel() {
        int lastLevel = getLevel() - 1;
        return lastLevel * lastLevel * 5;
    }

    public static boolean equipArmor(Armor armor) {
        if (inventory.contains(armor)) {
            removeArmor();
            setArmor(armor);
            removeItem(armor);
            return true;
        }
        return false;
    }

    public static boolean equipWeapon(Weapon weapon) {
        if (inventory.contains(weapon)) {
            removeWeapon();
            setWeapon(weapon);
            removeItem(weapon);
            return true;
        }
        return false;
    }

    /**
     * @param potion
     * @return
     */
    public static boolean use(Potion potion) {
        if (getInventory().contains(potion)) {
            if (potion.healsHp()) {
                int maxHp = calculateHp(getLevel());
                setHp(getHp() + (int)(maxHp * (potion.getStrength() / 100.00)));
                if (getHp() > maxHp) setHp(maxHp);
            } else {
                int maxMp = calculateMp(getLevel());
                setMp(getMp() + (int)(maxMp * (potion.getStrength() / 100.00)));
                if (getMp() > maxMp) setMp(maxMp);
            }
            removeItem(potion);
            return true;
        }
        return false;
    }

    /**
     * @param enemy enemy inventory is added to the Player's inventory, any currency is added to the Player's currency
     */
    public static void loot(Enemy enemy) {
        List<Item> spoils = enemy.getInventory();
        for (Item item : spoils) {
            getItem(item);
        }
        acquireCurrency(enemy.getCurrencyAmount());
    }

    /**
     * @param enemy
     * @return the amount of XP to be earned by defeating an enemy at the given level
     */
    public static void getEarnedXp(Enemy enemy) {
        int enemyLevel = enemy.getLevel();
        int earnedXp = enemyLevel * enemyLevel;
        setXp(getXp() + earnedXp);
        if (getXp() > calculateXpToNextLevel()) levelUp();
    }

    /**
     * Adds the equipped armor and weapon to the player's inventory and sets armor and weapon to null
     */
    public static void removeAll() {
        removeArmor();
        removeWeapon();
    }

    /**
     * Adds the equipped armor to the character's inventory and sets armor to null
     */
    public static void removeArmor() {
        if (armor != null) {
            getItem(armor);
            armor = null;
        }
    }

    /**
     * Adds the equipped weapon to the character's inventory and sets weapon to null
     */
    public static void removeWeapon() {
        if (weapon != null) {
            getItem(weapon);
            weapon = null;
        }
    }

    public static int getDefense() {
        return armor.getDefense();
    }

    public static int getLevel() {
        return level;
    }

    public static List<Attack> getAttackList() {
        return weapon.getAttackList();
    }

    public static Armor getArmor() {
        return armor;
    }

    public static Weapon getWeapon() {
        return weapon;
    }

    public static int getHp() {
        return hp;
    }

    public static void getItem(Item item) {
        inventory.add(item);
    }

    public static void removeItem(Item item) {
        inventory.remove(item);
    }

    public static String getName() {
        return name;
    }

    public static String getDescription() {
        return description;
    }

    public static List<Item> getInventory() {
        return inventory;
    }

    public static int getCurrencyAmount() {
        return currency;
    }

    public static void acquireCurrency(int incomingCurrency) {
        currency += incomingCurrency;
    }

    public static void setArmor(Armor playerArmor) {
        armor = playerArmor;
    }

    public static void setWeapon(Weapon playerWeapon) {
        weapon = playerWeapon;
    }

    public static void setHp(int playerHp) {
        hp = playerHp;
    }

    public static int getMp() {
        return mp;
    }

    public static int getXp() {
        return xp;
    }

    public static void setMp(int playerMp) {
        mp = playerMp;
    }

    public static void setXp(int playerXp) {
        xp = playerXp;
    }

    public static void setName(String playerName) {
        name = playerName;
    }

    public static void setDescription(String playerDescription) {
        description = playerDescription;
    }

    public static void setCurrency(int playerCurrency) {
        currency = playerCurrency;
    }

}
