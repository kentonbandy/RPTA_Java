package org.kentonbandy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kentonbandy.action.Attack;

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
        map.put("attack", new ArrayList<>(List.of(line1, line2, line3)));
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
}
