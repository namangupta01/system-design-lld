package commands;

import exceptions.InvalidCommandException;
import models.Command;
import models.Slot;
import service.OutputPrinter;
import service.ParkingLotService;

import java.util.List;

public class ColorToSlotNumberCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "slot_numbers_for_cars_with_colour";

    public ColorToSlotNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean isValidCommand(Command command) {
        return command.getParams().size() == 2 && command.getParams().get(0).equals(COMMAND_TYPE);
    }

    public void execute(Command cmd) throws InvalidCommandException {
        if (!isValidCommand(cmd)) throw new InvalidCommandException();
        String color = cmd.getParams().get(1);
        List<String> slotNumbers  = parkingLotService.getSlotsForColor(color).stream().map(slot -> slot.getSlotNumber().toString()).toList();
        outputPrinter.printWithNewLine(String.join(" ", slotNumbers));
    }
}
