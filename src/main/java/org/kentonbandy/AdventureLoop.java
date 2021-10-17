package org.kentonbandy;

public class AdventureLoop {
    private static Location currentLocation;


    public static void run() {
        while (true) {
            CliPrinter.atLocation(currentLocation);
            String input = CliPrinter.prompt();
        }
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(Location location) {
        currentLocation = location;
    }
}
