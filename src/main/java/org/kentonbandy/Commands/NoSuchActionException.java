package org.kentonbandy.Commands;

public class NoSuchActionException extends Exception{
    public NoSuchActionException() {
        super("No such command");
    }
}
