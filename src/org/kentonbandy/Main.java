package org.kentonbandy;

import org.kentonbandy.action.*;
import org.kentonbandy.character.*;
import org.kentonbandy.item.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create Locations
        Location whs = new Location("WHS", "You're in front of a red brick school that has bees painted all over the interior. The vice principal is walking toward you.");
        Location crossroads = new Location("Crossroads", "The road splits. To the North you see a yellow house. To the West you see a non-yellow house.");
        Location zachsHouse = new Location("Zach's House", "You arrive at a hulking structure in it's own forest on an unassuming street corner. A tiny white dog comes running at you. The only exit is south.");
        Location park = new Location("Park", "A bunch of kids are playing frisbee, swinging, making out, fighting, you know, what kids do. You take shelter from the sun under a Maple tree. The only exit is West.");
        Location farrsHouse = new Location("Farr's House", "An unambiguously yellow house stands before you. Farr's mom sees you and invites you in to have spaghetti for dinner.");
        Location travisHouse = new Location("Travis's House", "You're at the door of a house on the corner of an attractive development. Travis's mom opens the door and says \"I have no idea where Travis is but I just made brownies! Want one?\". You see another house to the West.");
        Location woodsHouse = new Location("Wood's House", "You approach a house with a fleet of various trucks, jeeps, and cars. Chances are Wood and Zach are inside.");

        // Populate maps (Location.addExit(String name, Location location))
        whs.addExit("West", crossroads);
        whs.addExit("North", zachsHouse);
        whs.addExit("East", park);
        crossroads.addExit("North", farrsHouse);
        crossroads.addExit("West", travisHouse);
        crossroads.addExit("East", whs);
        zachsHouse.addExit("South", whs);
        park.addExit("West", whs);
        farrsHouse.addExit("South", crossroads);
        travisHouse.addExit("East", crossroads);
        travisHouse.addExit("West", woodsHouse);
        woodsHouse.addExit("East", travisHouse);

        Attack bash = new Attack("Backpack Bash", 2);
        Attack thwack = new Attack("Thwack", 4);
        Attack blast = new Attack("Blast", 6);

        // Create Items
        Potion potion = new Potion("Potion", "A vial of liquid health. Heals HP.", 10, 10, true);
        Potion mPotion = new Potion("Magic Potion", "A vial of liquid magic. Replenishes MP.", 10, 10, false);
        Potion brownie = new Potion("\"Magic\" Brownie", "Travis's mom might have neglected to mention a key ingredient in this dank brownie. It's unclear what effect it will have.", 20, 100, false);
        Armor shirt = new Armor("Shirt", "A black tee shirt with System of a Down on the front.", 5, 3);
        Armor leatherJacket = new Armor("Leather Jacket", "A brown leather jacket.", 10, 7);
        Armor helmet = new Armor("Police Helmet", "A flimsy toy police helmet. It offers a surprising amount of protection.", 15, 10);
        Weapon backpack = new Weapon("Backpack", "A worn-out Jansport backpack full of books.", 2, bash);
        Weapon stick = new Weapon("Stick", "An stick about the length of your forearm. It seems pretty sturdy.", 5, thwack);
        Weapon mellophone = new Weapon("Mellophone", "It's like a french horn for marching band, also played by Chuck Mangione. These are its total use cases outside of combat.", 15, blast);

        List<Item> goblinInventory = new ArrayList();
        goblinInventory.add(potion);
        Enemy goblin = new Enemy("Goblin", "A green, gooey goblin.", 7, goblinInventory, 1, leatherJacket, stick);
        Player player = new Player("Kenny", "A foolhardy youth who probably needs to study more.", 50, new ArrayList<Item>(), 1, shirt, backpack);

        int defaultLocationWidth = 60;

        UI.atLocation(whs, defaultLocationWidth);
        UI.newLines(2);
        UI.printInventory(player);
        player.loot(goblin);
        UI.newLines(1);
        UI.printInventory(player);



        /*
        Post-battle actions:
        Enemy.die();
        Player.loot(Enemy);
         */


    }
}
