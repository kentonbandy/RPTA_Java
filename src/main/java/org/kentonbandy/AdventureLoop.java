package org.kentonbandy;

import org.kentonbandy.UI.CliOut;

public class AdventureLoop {
    private static Location currentLocation;


    public static void run() {
        while (true) {
            CliOut.atLocation(currentLocation);
            String input = CliOut.prompt();
        }
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(Location location) {
        currentLocation = location;
    }
}
