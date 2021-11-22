package org.kentonbandy.UI;

import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Item;

import java.util.Scanner;

public class CliIn implements Input {
    private final Scanner scanner = new Scanner(System.in);
    private Output out = new CliOut();

    public String prompt() {
        out.arrows();
        String input = scanner.nextLine().toLowerCase();
        return input;
    }

    public Item prompt(ShopOwner shop) throws LeaveException {
        out.arrows();
        String input = scanner.nextLine().toLowerCase();
        for (String keyword : new String[]{"buy ", "get ", "purchase "}) {
            if (input.contains(keyword)) {
                input = input.replace(keyword, "");
            }
        }
        if (input.equalsIgnoreCase("leave")) throw new LeaveException();
        for (Item item : shop.getInventory()) {
            if (item.getName().equalsIgnoreCase(input)) return item;
        }
        return null;
    }

    public boolean promptYN(String message) {
        out.line(message);
        out.arrows();
        String input = scanner.nextLine();
        return input.toLowerCase().equals("y");
    }

    @Override
    public boolean quit() {
        return promptYN("Are you sure you want to quit? (y/n)");
    }
}
