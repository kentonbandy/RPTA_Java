package org.kentonbandy.Commands;

public class EmptyCommandException extends Exception {
    public EmptyCommandException() {
        super("Please type a command.");
    }
}
