package org.kentonbandy.item;

public class Currency extends Item {
    private static String currencyName = "Gold";

    public Currency(String name, String description, int price) {
        super(name, description, price);
    }

    public Currency(int price) {
        super(currencyName, "Shiny golden coins.", price);
    }

    public boolean isCurrency() {
        return true;
    }

    public static void setCurrencyName(String name) {
        currencyName = name;
    }

    public static String getCurrencyName() {
        return currencyName;
    }
}
