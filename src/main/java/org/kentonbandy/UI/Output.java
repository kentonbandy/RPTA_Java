package org.kentonbandy.UI;

import org.kentonbandy.Location;
import org.kentonbandy.character.Player;

public interface Output {
    void atLocation(Location currentLocation);
    void levelUp(Player player);
    String wordWrap(String string, int width);
    void newLines(int num);
    void printUppercase(String string);
    void printInventory(Player player);
    void setDefaultWidth(int width);
    void error(String message);
}