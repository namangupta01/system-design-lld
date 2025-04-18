package commands;

import exceptions.InvalidCommandException;
import models.Car;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;

import java.util.List;


public class ColorToRegNumberCommandExecutor extends AbstractCommandExecutor {

    public static final String COMMAND_TYPE = "registration_numbers_for_cars_with_colour";

    public ColorToRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean isValidCommand(Command cmd) {
        return cmd.getParams().size() == 2 && cmd.getParams().get(0).equals(COMMAND_TYPE);
    }

    public void execute(Command cmd) throws InvalidCommandException {
        if(!isValidCommand(cmd)) throw new InvalidCommandException();
        String color = cmd.getParams().get(1);
        List<Car> cars = parkingLotService.getCarsForColor(color);
        List<String> carRegistrationNumbers = cars.stream().map(Car::getRegistrationNumber).toList();
        outputPrinter.printWithNewLine(String.join(" ", carRegistrationNumbers));
    }
}

