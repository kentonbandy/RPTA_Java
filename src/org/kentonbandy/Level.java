package org.kentonbandy;

import org.kentonbandy.item.*;

public abstract class Level {

    public static int getHp(int level) {
        return 10 + (level * level);
    }

    public static int getMp(int level) {
        return 8 * level;
    }

    public static int getEnemyHp(int level) {
        return 5 + (level * Math.max(1, level - 2));
    }

    /**
     * @param level
     * @param weapon
     * @return calculates and returns an attack power value based on level and weapon power
     */
    public static int getAttackPower(int level, Weapon weapon) {
        return (level * 2) + weapon.getPower();
    }

    /**
     * @param enemyLevel
     * @return the amount of XP to be earned by defeating an enemy at the given level
     */
    public static int getXp(int enemyLevel) {
        return enemyLevel * enemyLevel;
    }

    /**
     * @param currentLevel
     * @return the amount of XP required to reach the next player level
     */
    public static int nextLevel(int currentLevel) {
        return currentLevel * currentLevel * 5;
    }
}
