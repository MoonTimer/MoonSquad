package net.minecraft.command;

public class CommandException extends Exception {
    private Object[] errorObjects;

    public CommandException(String message, Object... objects) {
        super(message);
        this.errorObjects = objects;
    }

    public CommandException(String message) {
        super(message);
    }

    public Object[] getErrorObjects() {
        return this.errorObjects;
    }
}
