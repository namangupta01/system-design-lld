package commands;

import exceptions.InvalidCommandException;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;

public class ExitCommandExecutor extends AbstractCommandExecutor {
    public static final String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValidCommand(Command cmd) {
        return cmd.getParams().get(0).contentEquals(COMMAND_NAME);
    }

    public void execute(Command command) throws InvalidCommandException {
        if(!isValidCommand(command)) throw new InvalidCommandException();
    }

}
