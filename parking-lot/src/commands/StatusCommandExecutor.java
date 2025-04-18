package commands;

import exceptions.InvalidCommandException;
import models.Command;
import models.Slot;
import service.OutputPrinter;
import service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean isValidCommand(Command cmd) {
        return !cmd.getParams().isEmpty() && COMMAND_TYPE.equals(cmd.getParams().get(0));
    }

    @Override
    public void execute(Command cmd) throws InvalidCommandException {
        if(!isValidCommand(cmd)) throw new InvalidCommandException();
        List<Slot> slots  = parkingLotService.getSlots();
        outputPrinter.printParkingLotStatus(slots);
    }
}
