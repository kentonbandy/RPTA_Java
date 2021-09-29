package org.kentonbandy.character;

import org.kentonbandy.*;
import org.kentonbandy.action.*;
import org.kentonbandy.item.*;

import java.util.List;

public class Enemy extends Character {
    private int level;
    private Attack attack;
    private List<Armor> armor;
    private Weapon weapon;
    private int hp;

    public Enemy(String name, String description, List<Item> inventory, int level, Attack attack, List<Armor> armor,
                 Weapon weapon) {
        super(name, description, inventory);
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

    public List<Armor> getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}