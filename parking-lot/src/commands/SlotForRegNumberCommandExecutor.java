package commands;

import exceptions.InvalidCommandException;
import models.Command;
import models.Slot;
import service.OutputPrinter;
import service.ParkingLotService;

public class SlotForRegNumberCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "slot_number_for_registration_number";

    public SlotForRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean isValidCommand(Command command) {
        return command.getCommandType().equals(COMMAND_TYPE) && command.getParams().size() == 2;
    }

    public void execute(Command cmd) throws InvalidCommandException {
        if (!isValidCommand(cmd)) throw new InvalidCommandException();
        String regNum = cmd.getParams().get(1);
        Slot slot = parkingLotService.getSlotNumber(regNum);
        if (slot == null) {
            outputPrinter.invalidRegistrationNumber(regNum);
        } else {
            outputPrinter.slotForRegistration(regNum, slot.getSlotNumber());
        }
    }
}
