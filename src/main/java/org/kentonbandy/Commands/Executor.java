package org.kentonbandy.Commands;

import org.kentonbandy.Location;
import org.kentonbandy.UI.*;
import org.kentonbandy.World;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.Map;

public class Executor {
    private final Input input = new CliIn();
    private final Output output = new CliOut();

    public Executor() {}

    public Location execute(Command command, World world, Player player, Location currentLocation) {
        String act = command.getAction();
        String obj = command.getObject();
        String tar = command.getTarget();
        Location newLocation = null;

        if ((act.equals("move") || act.equals("travel") || act.equals("go")) && obj != null) {
            newLocation = move(command.getObject(), currentLocation, world.getLocations());
        }

        else if (act.equals("look")) look(currentLocation);

        else if (act.equals("examine")) examine(obj, world.getAggregateItems(), currentLocation, player);

        else if (act.equals("get") || act.equals("take") || act.equals("grab")) {
            get(obj, world.getAggregateItems(), currentLocation, player);
        }

        else if (act.equals("drop") || act.equals("throw")) {
            drop(obj, world.getAggregateItems(), currentLocation, player);
        }

        else if (act.equals("equip")) equip(obj, world.getAggregateItems(), player);

        else if (act.equals("inventory")) inventory(player);

        else if (act.equals("equipped") || act.equals("equipment")) equipped(player);

        else if (act.equals("talk")) talk(obj, currentLocation, player);

        else if (act.equals("quit")) quit();

        else output.error("I don't recognize that command.");

        return newLocation;
    }

    private Location move(String direction, Location currentLocation, Map<String, Location> locations) {
        if (!currentLocation.getMap().containsKey(direction)) {
            output.error("You can't go that way.");
            return null;
        }
        String destination = currentLocation.getMap().get(direction);
        if (!locations.containsKey(destination)) {
            output.error("Data error: destination \"" + destination + "\" does not exist.");
            return null;
        } else {
            return locations.get(destination);
        }
    }

    // Look currently displays Location items as a manual test.
    // Actual implementation will either repeat location info or examine items
    private void look(Location currentLocation) {
        output.printLocationItems(currentLocation.getItems());
    }

    private void examine(String itemName, Map<String, Item> allItems, Location currentLocation, Player player) {
        Item item = allItems.get(itemName);
        if (currentLocation.getItems().contains(item) || player.getInventory().contains(item)) {
            output.examine(item);
        } else output.error("There is no " + itemName + " here.");
    }

    private void get(String itemName, Map<String, Item> allItems, Location currentLocation, Player player) {
        Item item = allItems.get(itemName);
        if (currentLocation.getItems().contains(item)) {
            player.getItem(item);
            currentLocation.removeItem(item);
            output.get(item.getName());
        } else {output.error("That item isn't here.");}
    }

    private void drop(String itemName, Map<String, Item> allItems, Location currentLocation, Player player) {
        Item item = allItems.get(itemName);
        if (player.getInventory().contains(item)) {
            currentLocation.addItem(item);
            player.removeItem(item);
            output.drop(item.getName());
        } else {output.error("You don't have that item");}
    }

    private void equip(String itemName, Map<String, Item> allItems, Player player) {
        Item item = allItems.get(itemName);
        System.out.println(item.getClass().getName());
        if (player.getInventory().contains(item)) {
            if (item.getClass().getName().equals("org.kentonbandy.item.Armor")) {
                player.equipArmor((Armor)item);
                output.printEquipment(player);
            } else if (item.getClass().getName().equals("org.kentonbandy.item.Weapon")) {
                player.equipWeapon((Weapon)item);
                output.printEquipment(player);
            } else {
                output.error("That can't be equipped");
            }
        } else {
            output.error("You don't have that item");
        }
    }

    private void inventory(Player player) {
        output.printInventory(player);
    }

    private void equipped(Player player) {
        output.printEquipment(player);
    }

    private void talk(String shopName, Location currentLocation, Player player) {
        if (currentLocation.getShop() != null && currentLocation.getShop().getName().equalsIgnoreCase(shopName)) {
            new ShopLoop().run(currentLocation.getShop(), player);
        } else if (false) {
            // interact with enemies/NPCs
        } else {
            output.error(shopName + " isn't here.");
        }
    }

    private void quit() {
        if (input.quit()) System.exit(0);
    }
}
