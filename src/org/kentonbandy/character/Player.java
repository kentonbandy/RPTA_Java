package org.kentonbandy.character;

import org.kentonbandy.Level;
import org.kentonbandy.action.*;
import org.kentonbandy.item.*;

import java.util.List;

public class Player extends Enemy {
    private  int mp;
    private  int xp;

    public Player(String name, String description, int currency, List<Item> inventory, int level, Attack attack, Armor armor,
                  Weapon weapon) {
        super(name, description, currency, inventory, level, attack, armor, weapon);
        setLevel(1);

        mp = Level.getMp(level);

    }

    public void levelUp() {
        setLevel(getLevel() + 1);
        this.setHp(Level.getHp(getLevel()));
        this.setMp(Level.getMp(getLevel()));
    }

    public void setLevel(int level) {
        super.setLevel(level);
        setHp(Level.getHp(getLevel()));
        setMp(Level.getMp(getLevel()));
    }

    public boolean use(Potion potion) {
        if (getInventory().contains(potion)) {
            if (potion.healsHp()) setHp(getHp() + potion.getStrength());
            else setMp(getMp() + potion.getStrength());
            removeItem(potion);
            return true;
        }
        return false;
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
