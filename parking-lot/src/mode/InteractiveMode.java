package mode;

import commands.CommandExecutorFactory;
import commands.ExitCommandExecutor;
import exceptions.*;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode {

    public InteractiveMode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory) {
        super(outputPrinter, commandExecutorFactory);
    }

    @Override
    public void process() throws InvalidCommandException, IOException, NoFreeSlotAvailableException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException, ParkingLotAlreadyExistException {
        this.outputPrinter.welcome();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        while(command != null) {
            processCommand(new Command(command));
            if(command.equals(ExitCommandExecutor.COMMAND_NAME)) {
                outputPrinter.exit();
                return;
            }
            command = reader.readLine();
        }

    }
}
