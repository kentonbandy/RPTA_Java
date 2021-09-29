package org.kentonbandy.character;

import org.kentonbandy.Level;
import org.kentonbandy.action.*;
import org.kentonbandy.item.*;

import java.util.List;

public class Player extends Enemy {
    private int mp;
    private int xp;

    public Player(String name, String description, int currency, List<Item> inventory, int level, Attack attack, Armor armor,
                  Weapon weapon) {
        super(name, description, currency, inventory, level, attack, armor, weapon);
        setLevel(1);

        mp = Level.getMp(level);

    }

    public void levelUp() {
        setLevel(getLevel() + 1);
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
