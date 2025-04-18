package models;

import exceptions.InvalidCommandException;

import java.util.List;

public class Command {
    private static final String SPACE = " ";
    private final String command;
    private final List<String> params;

    public Command(String command) throws InvalidCommandException {
        this.command = command;
        this.params = List.of(command.trim().split(SPACE));

        if(this.params.isEmpty()) {
            throw new InvalidCommandException();
        }
    }

    public String getCommand() {
        return command;
    }

    public String getCommandType() {
        return params.get(0);
    }

    public List<String> getParams() {
        return params;
    }

}
