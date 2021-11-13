package org.kentonbandy.UI;

import org.kentonbandy.Location;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Item;

import java.util.List;

public interface Output {
    void atLocation(Location currentLocation);
    void levelUp(Player player);
    String wordWrap(String string, int width);
    void newLines(int num);
    void printUppercase(String string);
    void printInventory(Player player);
    void setDefaultWidth(int width);
    void error(String message);
    void printLocationItems(List<Item> items);
    void get(String itemName);
    void arrows();
    void line(String message);
    void examine(Item item);
}