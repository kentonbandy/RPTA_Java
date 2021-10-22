package org.kentonbandy;

import org.junit.Assert;
import org.junit.Test;
import org.kentonbandy.action.Attack;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.List;

public class ItemTests {

    @Test
    public void getItemType_returns_appropriate_string() {
        Attack attack = new Attack("Slash", "A powerful slash cuts through flesh.", 8);
        Item item = new Item("Potion", "A purple liquid in a vial.", 10, true);
        Armor armor = new Armor("Chest Plate", "A sturdy chest plate.", 12, 10, true);
        Weapon weapon = new Weapon("Sword", "A shiny, pointy sword.", 11, List.of(attack), true);

        Assert.assertEquals("Item", item.getItemType());
        Assert.assertEquals("Armor", armor.getItemType());
        Assert.assertEquals("Weapon", weapon.getItemType());
    }
}
