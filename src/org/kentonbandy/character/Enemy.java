package org.kentonbandy.character;

import org.kentonbandy.*;
import org.kentonbandy.action.*;
import org.kentonbandy.item.*;

import java.util.List;

public class Enemy extends Character {
    private int level;
    private Attack attack;
    private Armor armor;
    private Weapon weapon;
    private int hp;

    public Enemy(String name, String description, int currency, List<Item> inventory, int level, Attack attack,
                 Armor armor, Weapon weapon) {
        super(name, description, currency, inventory);
        this.level = level;
        this.attack = attack;
        this.armor = armor;
        this.weapon = weapon;
        this.hp = Level.getHp(level);
    }

    public int getLevel() {
        return level;
    }

    public Attack getAttack() {
        return attack;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHp() {
        return hp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}