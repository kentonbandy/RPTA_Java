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
    private Item triggerItem;

    /**
     * @param name
     * @param description
     * @param currency
     * @param inventory
     * @param level
     * @param armor
     * @param weapon
     */
    public Enemy(String name, String description, int currency, List<Item> inventory, int level, Armor armor,
                 Weapon weapon) {
        super(name, description, currency, inventory);
        this.level = level;
        this.armor = armor;
        this.weapon = weapon;
        this.hp = calculateHp(level);
        this.triggerItem = null;
    }

    public int calculateHp(int level) {
        return 10 + (level * level);
    }

    public void die() {
        unequipArmor();
        unequipWeapon();
    }

    public void unequipArmor() {
        getItem(armor);
        armor = null;
    }

    public void unequipWeapon() {
        getItem(weapon);
        weapon = null;
    }

    public int getAttackPower(int level) {
        return (level * 2) + weapon.getPower();
    }

    public int getDefense() {
        return armor.getDefense();
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

    public void setTriggerItem(Item item) {
        triggerItem = item;
    }
}