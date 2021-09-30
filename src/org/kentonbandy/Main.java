package org.kentonbandy;

public class Main {

    public static void main(String[] args) {
        Location whs = new Location("WHS", "You're in front of a red brick school that has bees painted all over the interior. The vice principal is walking toward you.");
        Location crossroads = new Location("Crossroads", "The road splits. To the North you see a yellow house. To the West you see a non-yellow house.");
        Location zachsHouse = new Location("Zach's House", "You arrive at a hulking structure in it's own forest on an unassuming street corner. A tiny white dog comes running at you. The only exit is south.");
        Location park = new Location("Park", "A bunch of kids are playing frisbee, swinging, making out, fighting, you know, what kids do. You take shelter from the sun under a Maple tree. The only exit is West.");
        Location farrsHouse = new Location("Farr's House", "An unambiguously yellow house stands before you. Farr's mom sees you and invites you in to have spaghetti for dinner.");
        Location travisHouse = new Location("Travis's House", "You're at the door of a house on the corner of an attractive development. Travis's mom opens the door and says \"I have no idea where Travis is but I just made brownies! Want one?\". You see another house to the West.");
        Location woodsHouse = new Location("Wood's House", "You approach a house with a fleet of various trucks, jeeps, and cars. Chances are Wood and Zach are inside.");

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

        UI.atLocation(whs);
    }
}
