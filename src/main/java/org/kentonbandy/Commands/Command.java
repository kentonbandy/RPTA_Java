package org.kentonbandy.Commands;

public class Command {
    private String action;
    private String object = null;
    private String target = null;

    public Command(String action) {
        this.action = action;
    }

    public Command(String action, String object) {
        this(action);
        this.object = object;
    }

    public Command(String action, String object, String target) {
        this(action, object);
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public String getObject() {
        return object;
    }

    public String getTarget() {
        return target;
    }
}
