package org.kentonbandy.UI;

import java.util.Scanner;

public class CliIn implements Input {
    private final Scanner scanner = new Scanner(System.in);

    public String prompt() {
        System.out.print(">>> ");
        String input = scanner.nextLine();
        return input.toLowerCase();
    }
}
