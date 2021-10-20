package org.kentonbandy;

import org.junit.*;
import org.kentonbandy.DAO.CsvLoader;
import org.kentonbandy.DAO.DataLoader;

import java.util.List;
import java.util.Map;

public class CsvLoaderTests {
    @Test
    public void loadGameData_creates_good_map_file() {
        DataLoader loader = new CsvLoader();
        Map<String, List<String>> gameFile = loader.loadGameData();

        String result1 = gameFile.get("attack").get(4);
        String expected1 = "Blast|A disorienting sonic blast!|6";

        String result2 = gameFile.get("item").get(0);
        String expected2 = "Snack|Your favorite pre-packaged salty snack. Heals HP by 40% up to max.|10|40|true";

        String result3 = gameFile.get("armor").get(7);
        String expected3 = "Jock Strap|You know what it is. Protects the wearer from the most severe of attacks|4|2";

        String result4 = gameFile.get("weapon").get(4);
        String expected4 = "Discolored Collar|At first glance, this collar looks normal. But upon further inspection, one finds that it enables special abilities!|2|Threatening Aura,Ankle Bite,Pee on Leg";

        String result5 = gameFile.get("enemy").get(1);
        String expected5 = "Tank|A small, white dog with an ironic name. Wears an interesting collar.|50|Snack,Soda|3|Fur Coat|Discolored Collar";

        String result6 = gameFile.get("shop owner").get(0);
        String expected6 = "Cashier|A recent high school graduate who looks like they'd rather not be there.|300|Snack,Soda|Whatever, just tell me what you want.|Come again, or not.";

        String result7 = gameFile.get("location").get(1);
        String expected7 = "Crossroads|The road splits. To the North you see a yellow house. To the West you see a non-yellow house.|false||Punk Kid,Nerd,Jock||north:Farr's House,west:Travis's House,east:WHS";

        Assert.assertEquals(expected1, result1);
        Assert.assertEquals(expected2, result2);
        Assert.assertEquals(expected3, result3);
        Assert.assertEquals(expected4, result4);
        Assert.assertEquals(expected5, result5);
        Assert.assertEquals(expected6, result6);
        Assert.assertEquals(expected7, result7);
    }
}
