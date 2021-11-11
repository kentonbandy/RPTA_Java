package org.kentonbandy.Commands;

import org.kentonbandy.Commands.Command;
import org.kentonbandy.Location;
import org.kentonbandy.UI.CliOut;
import org.kentonbandy.UI.Output;
import org.kentonbandy.character.Player;

import java.util.Map;

public class AdventureMethod {
    private Output out = new CliOut();

    public void execute(Command command, Map<String,Location> locations, Location currentLocation, Player player) {
        if (command.getAction().equals("move") && command.getObject() != null) move(command.getObject(), locations, currentLocation);
        else out.error("I don't recognize that command.");
    }

    public void move(String direction, Map<String,Location> locations, Location currentLocation) {
        if (!currentLocation.getMap().containsKey(direction)) {
            out.error("You can't go that way.");
            return;
        }
        String destination = currentLocation.getMap().get(direction);
        if (!locations.containsKey(destination)) {
            out.error("Data error: destination \"" + destination + "\" does not exist.");
        } else {
            currentLocation = locations.get(destination);
        }
    }
}
