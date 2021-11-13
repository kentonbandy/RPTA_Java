package org.kentonbandy.UI;


import org.kentonbandy.Location;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Currency;
import org.kentonbandy.item.Item;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class CliOut implements Output {
    private final Scanner scanner = new Scanner(System.in);
    private int defaultWidth = 60;

    public void atLocation(Location location, int wrapWidth) {
        newLines(1);
        printUppercase(location.getName());
        String desc = location.getDescription();
        out.println(wordWrap(desc, wrapWidth));
        newLines(1);
    }

    public void atLocation(Location location) {
        atLocation(location, defaultWidth);
    }

    public void levelUp(Player player) {
        newLines(1);
        printUppercase("level up!!!");
        newLines(1);
        out.println("Level " + player.getLevel() + "!");
        out.println("HP: " + player.calculateHp(player.getLevel() - 1) + " --> " + player.getHp());
        out.println("MP: " + player.calculateMp(player.getLevel() - 1) + " --> " + player.getMp());
        }

    public String wordWrap(String string, int width) {
        String[] wordArr = string.split(" ");
        String output = "";
        StringBuilder line = new StringBuilder();
        for (String word : wordArr) {
            int lineLen = line.length();
            int wordLen = word.length();
            int remainder = lineLen % width;
            if (remainder + wordLen < width && remainder > 0) {
                line.append(word).append(" ");
            } else {
                output += line.toString().strip() + "\n";
                line = new StringBuilder(word + " ");
            }
        }
        output += line.toString().strip();
        return output;
    }

    public String wordWrap(String string) {
        return wordWrap(string, 60);
    }

    public void newLines(int num) {
        for (num=num; num>0; num--) {
            out.println();
        }
    }

    public void printUppercase(String string) {
        out.println(string.toUpperCase());
    }

    public void printInventory(Player player) {
        List<Item> list = player.getInventory();
        int size = list.size();
        out.print("Inventory: ");
        if (size == 0) out.println("empty!");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) out.println(list.get(i).getName());
            else out.print(list.get(i).getName() + ", ");
        }
        out.println(Currency.getCurrencyName() + ": " + player.getCurrencyAmount());
    }

    public void printLocationItems(List<Item> items) {
        if (items == null || items.size() == 0) {
            out.println("No items found.");
            return;
        }
        String output = "";
        for (Item i : items) {
            output += i.getName() + ", ";
        }
        out.println(output.substring(0, output.length()-2));
    }

    public void get(String itemName) {
        out.println("You've picked up " + itemName + ".");
    }

    public void setDefaultWidth(int width) {
        defaultWidth = width;
    }

    public void error(String message) {
        out.println("\n!!! " + message + " !!!\n");
    }

    public void arrows() {
        out.print(">>> ");
    }

    public void line(String message) {
        out.println(message);
    }

    @Override
    public void examine(Item item) {
        line(item.getDescription());
    }
}
