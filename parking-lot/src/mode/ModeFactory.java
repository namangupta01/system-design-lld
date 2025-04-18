package mode;

import commands.CommandExecutorFactory;
import service.OutputPrinter;
import service.ParkingLotService;

public class ModeFactory {
    public static Mode getMode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory, String[] args) {
        if (args.length == 0) {
            return new InteractiveMode(outputPrinter, commandExecutorFactory);
        }
        return new FileMode(outputPrinter, commandExecutorFactory, args[0]);
    }
}
