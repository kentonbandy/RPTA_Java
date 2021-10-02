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

    /**
     * @param level
     * @return
     */
    public int calculateHp(int level) {
        return 10 + (level * level);
    }

    public void unequipAll() {
        unequipArmor();
        unequipWeapon();
    }

    /**
     * Adds the equipped armor to the character's inventory and sets armor to null
     */
    public void unequipArmor() {
        if (armor != null) {
            getItem(armor);
            armor = null;
        }
    }

    /**
     * Adds the equipped weapon to the character's inventory and sets weapon to null
     */
    public void unequipWeapon() {
        if (weapon != null) {
            getItem(weapon);
            weapon = null;
        }
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

    /**
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param attack
     */
    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    /**
     * @param armor
     */
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @param item
     */
    public void setTriggerItem(Item item) {
        triggerItem = item;
    }
}