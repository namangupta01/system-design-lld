package commands;

import exceptions.InvalidCommandException;
import exceptions.NoFreeSlotAvailableException;
import exceptions.ParkingLotAlreadyExistException;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;

import java.util.List;
import java.util.Objects;

public class ParkCarCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "park";

    public ParkCarCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean isValidCommand(Command cmd) throws InvalidCommandException {
        return cmd.getParams().size() != 3 && !Objects.equals(cmd.getParams().get(0), COMMAND_TYPE);
    }

    public void execute(Command cmd) {
        List<String> commandParams = cmd.getParams();
        String regNum = commandParams.get(1);
        String color = commandParams.get(2);
        try {
            Integer slotNumber = this.parkingLotService.park(regNum, color);
            this.outputPrinter.carParked(regNum, color, slotNumber);
        }
        catch (NoFreeSlotAvailableException e) {
            this.outputPrinter.noFreeSlotAvailable();
        }
    }
}
