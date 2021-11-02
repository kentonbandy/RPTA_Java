package org.kentonbandy.Commands;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("That item isn't here");
    }
}
