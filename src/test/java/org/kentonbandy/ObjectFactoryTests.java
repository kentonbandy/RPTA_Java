package org.kentonbandy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kentonbandy.DAO.ObjectFactory;
import org.kentonbandy.action.Attack;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactoryTests {
    private ObjectFactory factory;

    @Before
    public void startUp() {
        Map<String,List<String>> map = new HashMap<>();
        String line1 = "Zap|A laser gunshot to the groin!|5";
        String line2 = "Spitball|Gross! Someone Shot a spitball at you and it landed on your forehead!|2|3";
        String line3 = "bla|bla";
        String line4 = "Snack|Your favorite pre-packaged salty snack. Heals HP by 40% up to max.|10|true";
        String line5 = "Police Helmet|A flimsy toy police helmet. It offers a surprising amount of protection.|15|10";
        String line6 = "Glasses|A pair of bombastic bifocals.|5|Zap,Spitball";
        String line7 = "Bla|bla";

        map.put("attack", new ArrayList<>(List.of(line1, line2, line3)));
        map.put("item", List.of(line4));
        map.put("armor", List.of(line5));
        map.put("weapon", List.of(line6, line7));


        factory = new ObjectFactory(map);
    }

    @Test
    public void buildAttack_builds_attack() {
        Map<String,Attack> attackMap = factory.buildAttacksMap();
        Attack attack1 = attackMap.get("Zap");
        Attack attack2 = attackMap.get("Spitball");

        String result1 = attack1.getName();
        String expected1 = "Zap";
        String result2 = attack2.getDescription();
        String expected2 = "Gross! Someone Shot a spitball at you and it landed on your forehead!";
        int result3 = attack1.getPower();
        int expected3 = 5;
        int result4 = attack2.getMpCost();
        int expected4 = 3;
        int result5 = attack1.getMpCost();
        int expected5 = 0;

        Assert.assertEquals(expected1, result1);
        Assert.assertEquals(expected2, result2);
        Assert.assertEquals(expected3, result3);
        Assert.assertEquals(expected4, result4);
        Assert.assertEquals(expected5, result5);
        Assert.assertEquals(2, attackMap.keySet().size());
    }

    @Test
    public void buildItemsMap_builds_items() {
        Map<String,Item> itemMap = factory.buildItemMap();
        Item item = itemMap.get("Snack");
        Weapon weapon = (Weapon)itemMap.get("Glasses");

        String result1 = item.getName();
        String expected1 = "Snack";
        String result2 = item.getDescription();
        int result3 = item.getPrice();
        boolean result4 = item.isGettable();

        Assert.assertEquals("Snack", result1);
        Assert.assertEquals("Your favorite pre-packaged salty snack. Heals HP by 40% up to max.", result2);
        Assert.assertEquals(10, result3);
        Assert.assertTrue(result4);
    }

    @Test
    public void buildArmorMap_builds_armors() {
        Map<String,Armor> armorMap = factory.buildArmorMap();
        Armor armor = armorMap.get("Police Helmet");
        String result1 = armor.getName();
        String result2 = armor.getDescription();
        int result3 = armor.getPrice();
        int result4 = armor.getDefense();
        boolean result5 = armor.isGettable();

        Assert.assertEquals("Police Helmet", result1);
        Assert.assertEquals("A flimsy toy police helmet. It offers a surprising amount of protection.", result2);
        Assert.assertEquals(15, result3);
        Assert.assertEquals(10, result4);
        Assert.assertTrue(result5);
    }

    @Test
    public void buildWeaponMap_builds_weapons() {
        Map<String,Attack> attackMap = factory.buildAttacksMap();
        Map<String,Weapon> weaponMap = factory.buildWeaponMap(attackMap);
        Weapon weapon = weaponMap.get("Glasses");

        Assert.assertEquals("Glasses", weapon.getName());
        Assert.assertEquals("A pair of bombastic bifocals.", weapon.getDescription());
        Assert.assertEquals(5, weapon.getPrice());
        Assert.assertEquals(5, weapon.getPrice());
        Assert.assertEquals("Zap", weapon.getAttackList().get(0).getName());
    }
}
