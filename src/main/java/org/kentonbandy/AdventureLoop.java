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
    private Map<String,Item> allItems;
    private Map<String,Location> locations;
    private Location currentLocation;
    private final Player player;

    private final Parser parser = new Parser();
    private final Input input = new CliIn();
    private final Output output = new CliOut();

    public AdventureLoop(World world, Player player) {
        this.allItems = world.getAggregateItems();
        this.locations = world.getLocations();
        this.currentLocation = world.getStartLocation();
        this.player = player;
    }


    public void run() {
        while (true) {
            output.atLocation(currentLocation);
            Command command = null;
            try {
                command = parser.buildCommand(input.prompt(), allItems.keySet());
            } catch (NoSuchActionException | EmptyCommandException | ItemNotFoundException e) {
                output.error(e.getMessage());
            }
            if (command != null) {
                commandInfoHelper(command);
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
        String act = command.getAction();
        String obj = command.getObject();
        String tar = command.getTarget();

        if ((act.equals("move") || act.equals("travel")) && obj != null) move(command.getObject());

        else if (act.equals("look")) look();

        else if (act.equals("examine")) examine(obj);

        else if (act.equals("get")) get(obj);

        else if (act.equals("inventory")) inventory();

        else if (act.equals("quit")) quit();

        // Throw error
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

    // Look currently displays Location items as a manual test.
    // Actual implementation will either repeat location info or examine items
    private void look() {
        output.printLocationItems(currentLocation.getItems());
    }

    private void examine(String itemName) {
        Item item = allItems.get(itemName);
        if (currentLocation.getItems().contains(item) || player.getInventory().contains(item)) {
            output.examine(item);
        } else output.error("There is no " + itemName + " here.");
    }

    private void get(String itemName) {
        Item item = allItems.get(itemName);
        if (currentLocation.getItems().contains(item)) {
            player.getItem(item);
            currentLocation.removeItem(item);
            output.get(item.getName());
        } else {output.error("That item isn't here.");}
    }

    private void inventory() {
        output.printInventory(player);
    }

    private void quit() {
        if (input.quit()) System.exit(0);
    }
}
