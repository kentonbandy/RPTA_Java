package org.kentonbandy;

import org.kentonbandy.Commands.*;
import org.kentonbandy.UI.CliIn;
import org.kentonbandy.UI.CliOut;
import org.kentonbandy.UI.Input;
import org.kentonbandy.UI.Output;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdventureLoop {
    private Set<String> allItems;
    private Map<String,Location> locations;
    private Location currentLocation;
    private final Player player;

    private final Parser parser = new Parser();
    private final Input input = new CliIn();
    private final Output output = new CliOut();
    private AdventureMethod methods = new AdventureMethod();

    public AdventureLoop(World world, Player player) {
        this.allItems = world.getAggregateItems().keySet();
        this.locations = world.getLocations();
        this.currentLocation = world.getStartLocation();
        this.player = player;
    }


    public void run() {
        while (true) {
            output.atLocation(currentLocation);
            Command command = null;
            try {
                command = parser.buildCommand(input.prompt(), currentLocation);
            } catch (NoSuchActionException | EmptyCommandException | ItemNotFoundException e) {
                output.error(e.getMessage());
            }
            if (command != null) {
                execute(command);
            }
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    private void commandInfoHelper(Command command) {
        if (command != null) {
            System.out.println("Action: " + command.getAction());
            System.out.println("Object: " + command.getObject());
            System.out.println("Target: " + command.getTarget());
        }
    }



    private void execute(Command command) {
        if (command.getAction().equals("move") && command.getObject() != null) move(command.getObject());
        else output.error("I don't recognize that command.");
    }

    private void move(String direction) {
        if (!currentLocation.getMap().containsKey(direction)) {
            output.error("You can't go that way.");
            return;
        }
        String destination = currentLocation.getMap().get(direction);
        if (!locations.containsKey(destination)) {
            output.error("Data error: destination \"" + destination + "\" does not exist.");
        } else {
            currentLocation = locations.get(destination);
        }
    }
}
