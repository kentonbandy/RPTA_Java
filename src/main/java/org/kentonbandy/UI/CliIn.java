package org.kentonbandy.UI;

import org.kentonbandy.action.Attack;
import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Weapon;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class CliIn implements Input {
    private final Scanner scanner = new Scanner(System.in);
    private Output out = new CliOut();

    public String prompt() {
        out.arrows();
        String input = scanner.nextLine().toLowerCase();
        return input;
    }

    public void prompt(String message) {
        out.line(message);
        out.arrows();
        scanner.nextLine();
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

    @Override
    public String battlePrompt() {
        out.line("What would you like to do?");
        out.line("attack|item|run");
        return prompt();
    }

    public String attackPrompt(List<Attack> attacks) {
        out.line("Choose an attack:");
        String output = "";
        for (Attack attack : attacks) {
            output += attack.getName() + "|";
        }
        out.line(output.substring(0,output.length()-2));
        out.newLines(1);
        return prompt();
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
