package commands;

import exceptions.*;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;


public abstract class AbstractCommandExecutor implements CommandExecutor {
    protected final ParkingLotService parkingLotService;
    protected final OutputPrinter outputPrinter;

    public AbstractCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    protected abstract boolean isValidCommand(Command cmd) throws CommandTypeMismatchException, InvalidCommandException;
}
