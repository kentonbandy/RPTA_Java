package org.kentonbandy.Commands;

public class ObjectNotFoundExeption extends Throwable {

    public ObjectNotFoundExeption() {
        super("You can't do that");
    }
}
