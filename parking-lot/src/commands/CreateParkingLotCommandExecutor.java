package commands;

import exceptions.CommandTypeMismatchException;
import exceptions.InvalidCommandException;
import exceptions.ParkingLotAlreadyExistException;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;
import validator.ValidatorUtils;

public class CreateParkingLotCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "create_parking_lot";
    Integer capacity;

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean isValidCommand(Command command) {
        String commandType = command.getParams().get(0);
        return commandType.equals(COMMAND_TYPE) && ValidatorUtils.isValidInteger(command.getParams().get(1));
    }

    @Override
    public void execute(Command cmd) throws ParkingLotAlreadyExistException, InvalidCommandException {
        if(!isValidCommand(cmd)) throw new InvalidCommandException();
        try {
            parkingLotService.createParkingLot(Integer.parseInt(cmd.getParams().get(1)));
            this.outputPrinter.parkingLotCreated();
        }
        catch (ParkingLotAlreadyExistException e) {
            outputPrinter.parkingLotAlreadyCreated();;
        }

    }
}
