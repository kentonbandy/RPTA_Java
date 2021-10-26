package org.kentonbandy;

import org.kentonbandy.UI.CliInOut;

public class AdventureLoop {
    private Location currentLocation;

    public void run() {
        while (true) {
            CliInOut.atLocation(currentLocation);
            String input = CliInOut.prompt();
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }
}
