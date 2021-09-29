package org.kentonbandy;

import org.kentonbandy.item.*;

public abstract class Level {

    public static int getHp(int level) {
        return 10 + (level * level);
    }

    public static int getAttackPower(int level, Weapon weapon) {
        return (level * 2) + weapon.getPower();
    }

}
