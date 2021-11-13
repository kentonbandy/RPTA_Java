package org.kentonbandy.UI;

import java.util.Scanner;

public class CliIn implements Input {
    private final Scanner scanner = new Scanner(System.in);
    private Output out = new CliOut();

    public String prompt() {
        out.arrows();
        String input = scanner.nextLine();
        return input.toLowerCase();
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
