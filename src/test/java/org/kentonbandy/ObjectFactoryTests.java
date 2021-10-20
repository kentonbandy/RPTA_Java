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
        Map<String,Attack> attackList = factory.buildAttacksMap();
        Attack attack1 = attackList.get("Zap");
        Attack attack2 = attackList.get("Spitball");

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
        Assert.assertEquals(2, attackList.keySet().size());
    }

    @Test
    public void buildItemsMap_builds_items() {
        Map<String,Attack> attacksMap = factory.buildAttacksMap();
        Map<String,Item> itemMap = factory.buildItemsMap(attacksMap);


        Item item = itemMap.get("Snack");
        Armor armor = (Armor)itemMap.get("Police Helmet");
        Weapon weapon = (Weapon)itemMap.get("Glasses");

        String result1 = item.getName();
        String expected1 = "Snack";
        String result2 = armor.getDescription();
        String expected2 = "A flimsy toy police helmet. It offers a surprising amount of protection.";
        int result3 = weapon.getPrice();
        int expected3 = 5;
        int result4 = weapon.getPower(weapon.getAttackList().get(0));
        int expected4 = 5;
        int result5 = itemMap.size();
        int expected5 = 3;
    }
}
