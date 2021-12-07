package org.kentonbandy;

import org.kentonbandy.Commands.*;
import org.kentonbandy.UI.*;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Item;


import java.util.Map;

public class AdventureLoop {
    private final World world;
    private final Map<String,Item> allItems;
    private Location currentLocation;
    private final Player player;

    private final Parser parser = new Parser();
    private final Input input = new CliIn();
    private final Output output = new CliOut();
    private final ShopLoop shopLoop = new ShopLoop();
    private final Executor exec = new Executor();

    public AdventureLoop(World world, Player player) {
        this.world = world;
        this.allItems = world.getAggregateItems();
        this.currentLocation = world.getStartLocation();
        this.player = player;
    }


    public void run() {
        while (true) {
            output.atLocation(currentLocation);
            Command command = null;
            try {
                command = parser.buildCommand(input.prompt(), allItems.keySet(), currentLocation);
            } catch (NoSuchActionException | EmptyCommandException | ItemNotFoundException | ObjectNotFoundExeption e) {
                output.error(e.getMessage());
            }
            if (command != null) {
                commandInfoHelper(command);
                Location loc = exec.execute(command, world, player, currentLocation);
                if (loc != null) {
                    currentLocation = loc;
                }
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
}
