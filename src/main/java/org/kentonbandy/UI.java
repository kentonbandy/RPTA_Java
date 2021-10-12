package org.kentonbandy;


import org.kentonbandy.character.Player;
import org.kentonbandy.item.Currency;
import org.kentonbandy.item.Item;

import java.util.List;
import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);

    private static int defaultWidth = 60;

    public static void atLocation(Location location, int wrapWidth) {
        newLines(20);
        printUppercase(location.getName());
        String desc = location.getDescription();
        System.out.println(wordWrap(desc, wrapWidth));
        newLines(1);
    }

    public static void atLocation(Location location) {
        atLocation(location, defaultWidth);
    }

    public static void levelUp() {
        newLines(1);
        printUppercase("level up!!!");
        newLines(1);
        System.out.println("Level " + Player.getLevel() + "!");
        System.out.println("HP: " + Player.calculateHp(Player.getLevel() - 1) + " --> " + Player.getHp());
        System.out.println("MP: " + Player.calculateMp(Player.getLevel() - 1) + " --> " + Player.getMp());
        }

    public static String wordWrap(String string, int width) {
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

    public static String wordWrap(String string) {
        return wordWrap(string, 60);
    }

    public static void newLines(int num) {
        for (num=num; num>0; num--) {
            System.out.println();
        }
    }

    public static void printUppercase(String string) {
        System.out.println(string.toUpperCase());
    }

    public static void printInventory() {
        List<Item> list = Player.getInventory();
        int size = list.size();
        System.out.print("Inventory: ");
        if (size == 0) System.out.println("empty!");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) System.out.println(list.get(i).getName());
            else System.out.print(list.get(i).getName() + ", ");
        }
        System.out.println(Currency.getCurrencyName() + ": " + Player.getCurrencyAmount());
    }

    public static void setDefaultWidth(int width) {
        defaultWidth = width;
    }

    public static String prompt() {
        System.out.print(">>> ");
        String input = scanner.nextLine();
        return input;
    }
}
