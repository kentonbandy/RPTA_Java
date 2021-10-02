package org.kentonbandy.character;

import org.kentonbandy.UI;
import org.kentonbandy.item.*;
import java.util.List;

public class Player extends Enemy {
    private  int mp;
    private  int xp;

    /**
     * @param name
     * @param description
     * @param currency
     * @param inventory
     * @param level
     * @param armor
     * @param weapon
     */
    public Player(String name, String description, int currency, List<Item> inventory, int level, Armor armor,
                  Weapon weapon) {
        super(name, description, currency, inventory, level, armor, weapon);
        setLevel(1);
        setHp(calculateHp(level));
        mp = calculateMp(level);
        xp = 0;
    }

    /**
     * Sets new level, Hp, and Mp values, all are set to full value
     */
    public void levelUp() {
        setLevel(getLevel() + 1);
        UI.levelUp(this);
    }

    /**
     * @param level
     * @return Max HP value for the given level
     */
    @Override
    public int calculateHp(int level) {
        return 10 + (level * level);
    }

    /**
     * @param level
     */
    public void setLevel(int level) {
        super.setLevel(level);
        setHp(calculateHp(getLevel()));
        setMp(calculateMp(getLevel()));
    }

    /**
     * @param level
     * @return Max MP value for the given level
     */
    public int calculateMp(int level) {
        return 8 * level;
    }

    public int calculateXpToNextLevel() {
        int currentLevel = getLevel();
        return currentLevel * currentLevel * 5;
    }

    public boolean equipArmor(Armor armor) {
        if (getInventory().contains(armor)) {
            unequipArmor();
            setArmor(armor);
            removeItem(armor);
            return true;
        }
        return false;
    }

    /**
     * @param potion
     * @return
     */
    public boolean use(Potion potion) {
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
    public void loot(Enemy enemy) {
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
    public void getEarnedXp(Enemy enemy) {
        int enemyLevel = enemy.getLevel();
        int earnedXp = enemyLevel * enemyLevel;
        setXp(getXp() + earnedXp);
        if (getXp() > calculateXpToNextLevel()) levelUp();
    }

    public int getMp() {
        return mp;
    }

    public int getXp() {
        return xp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
