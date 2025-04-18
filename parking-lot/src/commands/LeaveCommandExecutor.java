package commands;

import exceptions.InvalidCommandException;
import exceptions.InvalidSlotNumberException;
import exceptions.SlotNotOccupiedException;
import models.Car;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;
import validator.ValidatorUtils;

public class LeaveCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValidCommand(Command cmd) throws NumberFormatException {
        return cmd.getParams().size() == 2 && cmd.getParams().get(0).equals(COMMAND_TYPE) && ValidatorUtils.isValidInteger(cmd.getParams().get(1));
    }

    public void execute(Command cmd) throws InvalidCommandException, SlotNotOccupiedException, InvalidSlotNumberException {
        if(!isValidCommand(cmd)) throw new InvalidCommandException();
        Integer slotNumber = Integer.parseInt(cmd.getParams().get(1));
        try {
            Car car = parkingLotService.leave(slotNumber);
            outputPrinter.leave(car.getRegistrationNumber(), car.getColor(), slotNumber);
        } catch (InvalidSlotNumberException e) {
            outputPrinter.invalidSlotNumber(slotNumber);
        } catch (SlotNotOccupiedException e) {
            outputPrinter.slotNotOccupied(slotNumber);
        }

    }
}
