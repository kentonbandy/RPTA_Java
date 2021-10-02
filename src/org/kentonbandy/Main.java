package org.kentonbandy;

import org.kentonbandy.action.*;
import org.kentonbandy.character.*;
import org.kentonbandy.item.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static boolean enemiesDropEquipment = false;
    private static Location currentLocation;

    public static void main(String[] args) {
        // Create Locations
        Location whs = new Location("WHS", "You're in front of a red brick school that has bees painted all over the interior. The vice principal is walking toward you.");
        Location crossroads = new Location("Crossroads", "The road splits. To the North you see a yellow house. To the West you see a non-yellow house.");
        Location zachsHouse = new Location("Zach's House", "You arrive at a hulking structure in it's own forest on an unassuming street corner. A tiny white dog comes running at you. Farr's house is to the west, WHS is to the south, and a corner store is to the east.");
        Location insideZachsHouse = new Location("Inside Zach's House", "You're in a spacious house that you recognize as zach's. Tank's toys adorn much of the interior. Is that... a police helmet??");
        Location park = new Location("Park", "A bunch of kids are playing frisbee, swinging, making out, fighting, you know, what kids do. You take shelter from the sun under a Maple tree. WHS is to the west, a corner store is to the north.");
        Location cornerStore = new Location("Corner Store", "You've arrived at a Subway/gas station/convenience store. Usually a hot open lunch location for students, this shop has somehow run out of everything except potions and magic potions. The cashier eyes you suspiciously from the counter.");
        Location farrsHouse = new Location("Farr's House", "An unambiguously yellow house stands before you. Farr's mom sees you and invites you in to have spaghetti for dinner. Zach's house is to the east, the crossroads lie to the south.");
        Location travisHouse = new Location("Travis's House", "You're at the door of a house on the corner of an attractive development. Travis's mom opens the door and says \"I have no idea where Travis is but I just made brownies! Want one?\". You see another house to the West.");
        Location woodsHouse = new Location("Wood's House", "You approach a house with a fleet of various trucks, jeeps, and cars. Chances are Wood and Zach are inside.");

        // Populate maps (Location.addExit(String name, Location location))
        whs.addExit("west", crossroads);
        whs.addExit("north", zachsHouse);
        whs.addExit("east", park);
        crossroads.addExit("north", farrsHouse);
        crossroads.addExit("west", travisHouse);
        crossroads.addExit("east", whs);
        zachsHouse.addExit("south", whs);
        zachsHouse.addExit("east", cornerStore);
        zachsHouse.addExit("west", farrsHouse);
        // add exit to inside zachs house after tank is defeated
        insideZachsHouse.addExit("out", zachsHouse);
        park.addExit("west", whs);
        park.addExit("north", cornerStore);
        cornerStore.addExit("south", park);
        cornerStore.addExit("west", zachsHouse);
        farrsHouse.addExit("south", crossroads);
        farrsHouse.addExit("east", zachsHouse);
        travisHouse.addExit("east", crossroads);
        travisHouse.addExit("west", woodsHouse);
        woodsHouse.addExit("east", travisHouse);

        // Create Attacks
        // backpack
        Attack bash = new Attack("Backpack Bash", "A crushing blow with a backpack full of books!", 2);
        Attack throwBook = new Attack("Throw Book", "A hardcover textbook to the face!", 4, 5);
        // stick
        Attack thwack = new Attack("Thwack", "Slapped with a stick!", 3);
        Attack crisscross = new Attack("Crisscross", "Two thwacks in opposing directions!", 5, 7);
        // mellophone
        Attack blast = new Attack("Blast", "A disorienting sonic blast!", 6);
        Attack energyBurst = new Attack("Energy Burst", "The mellophone begins to glow as it absorbs energy ... and releases it in a devastating giant beam out of the bell!", 10, 15);
        // vice principal clip-on tie attacks
        Attack giveDetention = new Attack("Give Detention", "Straight to Detention!", 2);
        Attack callParents = new Attack("Call Parents", "Your child has been very very naughty!", 6, 5);
        // tank collar attacks
        Attack ankleBite = new Attack("Ankle Bite", "That's gonna hurt in the morning!", 3, 2);
        Attack threateningAura = new Attack("Threatening Aura", "Hostile vibes extend in all directions!", 1);
        Attack peeOnLeg = new Attack("Pee on Leg", "That smells awful!", 4, 4);
        // punk kid
        Attack callNames = new Attack("Call names", "Wow, name calling is so mean!", 2);
        Attack crudeJoke = new Attack("Crude Joke", "Part of a public school education!", 3);
        Attack threateningOdor = new Attack("Threatening Odor", "Breathing is painful", 4, 4);
        // nerd
        Attack correctsGrammar = new Attack("Corrects Grammar", "A grammatical error comes at a cost... in HP!", 1);
        Attack thickLenses = new Attack("Thick Lenses", "The sun catches the lenses at the perfect angle, creating a death ray", 3);
        Attack stealWifi = new Attack("Steal WiFi", "A laptop appears... password cracked... WiFi stolen!", 4, 4);
        // jock
        Attack muscleFlex = new Attack("Muscle Flex", "Those muscles are too big to handle!", 2);
        Attack football = new Attack("Football", "A football smashes into its target as only a third-stringer could throw it!", 3);
        Attack bench = new Attack("Bench", "You've been bench pressed! How does this cause damage? No one knows!", 5, 6);

        // Create Items
        Potion potion = new Potion("Potion", "A vial of liquid health. Heals HP by 40% up to max.", 10, 40, true);
        Potion mPotion = new Potion("Magic Potion", "A vial of liquid magic. Replenishes MP by 40% up to max.", 10, 40, false);
        Potion brownie = new Potion("\"Magic\" Brownie", "Travis's mom might have neglected to mention a key ingredient in this dank brownie. It's unclear what effect it will have.", 20, 100, false);
        Armor shirt = new Armor("Shirt", "A black tee shirt with System of a Down on the front.", 5, 3);
        Armor leatherJacket = new Armor("Leather Jacket", "A brown leather jacket.", 10, 7);
        Armor helmet = new Armor("Police Helmet", "A flimsy toy police helmet. It offers a surprising amount of protection.", 15, 10);
        Armor blazer = new Armor("Blazer", "This is something you'd wear to a formal occasion.", 20, 8);
        Armor furCoat = new Armor("Fur Coat", "The best natural armor a dog could ask for", 100, 4);
        Armor spikeChoker = new Armor("Spike Choker", "It's like a pointy dog collar but for humans. It's edgy, I guess.", 5, 2);
        Armor pocketProtector = new Armor("Pocket Protector", "Do they still make these things? A sturdy piece of plastic that goes in a shirt pocket and holds pens.", 1, 2);
        Armor jockstrap = new Armor("Jock Strap", "You know what it is. Protects the wearer from the most severe of attacks", 4, 2);
        Weapon backpack = new Weapon("Backpack", "A worn-out Jansport backpack full of books.", 2, List.of(bash, throwBook));
        Weapon stick = new Weapon("Stick", "An stick about the length of your forearm. It seems pretty sturdy.", 5, List.of(thwack, crisscross));
        Weapon mellophone = new Weapon("Mellophone", "It's like a french horn for marching band, also played by Chuck Mangione. These are its total use cases outside of combat.", 15, List.of(blast, energyBurst));
        Weapon clipOn = new Weapon("Clip-on Tie", "A fashion decision that is somehow simultaneously tacky and too formal for the setting.", 2, List.of(giveDetention, callParents));
        Weapon collar = new Weapon("Discolored Collar", "At first glance, this collar looks normal. But upon further inspection, one finds that this collar enables special abilities!", 2, List.of(threateningAura, ankleBite, peeOnLeg));
        Weapon angst = new Weapon("Angst", "A powerful anti-establishment force stemming from puberty and counter culture.", 2, List.of(callNames, crudeJoke, threateningOdor));
        Weapon glasses = new Weapon("Glasses", "A pair of bombastic bifocals.", 5, List.of(correctsGrammar, thickLenses, stealWifi));
        Weapon ego = new Weapon("Ego", "On game night, the entire town comes to cheer them on. This is big head energy.", 0, List.of(muscleFlex, football, bench));

        // Populate Locations with items
        park.addItem(stick);

        // Create Characters
        Player player = new Player("Kenny", "A foolhardy youth who probably needs to study more.", 50, new ArrayList<Item>(), 1, shirt, backpack);
        Enemy vicePrincipal = new Enemy("Vice Principal", "An overdressed man with a thick southern accent. From what you can tell, he despises kids.", 100, List.of(potion, mPotion), 6, blazer, clipOn);
        Enemy tank = new Enemy("Tank", "A small, white dog with an ironic name. Wears an interesting collar.", 50, List.of(potion, mPotion), 3, furCoat, collar);
        Enemy punk = new Enemy("Punk Kid", "A kid who's clearly looking for trouble. Wears a spike choker and is poised to battle with weaponized teen angst.", 10, List.of(), 1, spikeChoker, angst);
        Enemy nerd = new Enemy("Nerd", "This kid has everything: thick glasses, pocket protector, and it looks like there's a laptop in their backpack.", 10, List.of(), 1, pocketProtector, glasses);
        Enemy jock = new Enemy("Jock", "Great, a meathead who spends half their time in the weight room has challenged you to a fight. Good luck.", 15, List.of(), 2, jockstrap, ego);


        currentLocation = whs;
        String input = "";
        while (true) {
            UI.atLocation(currentLocation);
            input = UI.prompt();
            currentLocation = currentLocation.travel(input);
        }



        /*
        Game start screen
            -start game
            -tutorial
            -instructions
            -quit
        Start game
            -Enter name
            -Enter description
        Spawn at starting location
            -location description includes hints for characters and items present
            -bosses attack after x turns (boss counter?)
            -if location is an unsafe location, the player can be randomly attacked by spawned enemies
            -random enemies are generated in real time with RNG
            -the player can >wait at unsafe locations until enemies spawn
            -potions outside of battles replenish HP/MP fully, in battle they only replenish their set amount
            -location descriptions change based on the items and characters at the location
        Battles
            -player and enemy alternate taking actions
            -if enemy is vanquished, player gets spoils including currency and items that the enemy had
         */


        /*
        Post-battle actions:
        Enemy.die();
        Player.loot(Enemy);
         */


    }
}
