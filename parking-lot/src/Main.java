import commands.CommandExecutorFactory;
import exceptions.*;
import mode.ModeFactory;
import service.OutputPrinter;
import service.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InvalidCommandException, IOException, NoFreeSlotAvailableException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException, ParkingLotAlreadyExistException {
        OutputPrinter outputPrinter = new OutputPrinter();
        ParkingLotService parkingLotService = new ParkingLotService();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService, outputPrinter);
        ModeFactory.getMode(
                outputPrinter,
                commandExecutorFactory,
                args
        ).process();
    }
}