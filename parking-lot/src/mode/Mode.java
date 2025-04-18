package mode;

import commands.CommandExecutor;
import commands.CommandExecutorFactory;
import exceptions.*;
import models.Command;
import service.OutputPrinter;

import java.io.IOException;

public abstract class Mode {
    protected OutputPrinter outputPrinter;
    private final CommandExecutorFactory commandExecutorFactory;

    public Mode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory) {
        this.outputPrinter = outputPrinter;
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public abstract void process() throws IOException, InvalidCommandException, NoFreeSlotAvailableException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException, ParkingLotAlreadyExistException;

    public void processCommand(Command command) throws NoFreeSlotAvailableException, InvalidCommandException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException, ParkingLotAlreadyExistException {
        this.commandExecutorFactory.getCommandExecutor(command.getCommandType()).execute(command);
    }

}
