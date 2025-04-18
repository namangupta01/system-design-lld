package commands;

import service.OutputPrinter;
import service.ParkingLotService;

import java.util.HashMap;

public class CommandExecutorFactory {
    private final HashMap<String, CommandExecutor> executors;

    public CommandExecutorFactory(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.executors = new HashMap<>();
        this.executors.put(ParkCarCommandExecutor.COMMAND_TYPE, new ParkCarCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(LeaveCommandExecutor.COMMAND_TYPE, new LeaveCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(StatusCommandExecutor.COMMAND_TYPE, new StatusCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(CreateParkingLotCommandExecutor.COMMAND_TYPE, new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(ColorToRegNumberCommandExecutor.COMMAND_TYPE, new ColorToRegNumberCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(ColorToSlotNumberCommandExecutor.COMMAND_TYPE, new ColorToSlotNumberCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(SlotForRegNumberCommandExecutor.COMMAND_TYPE, new SlotForRegNumberCommandExecutor(parkingLotService, outputPrinter));
        this.executors.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(String commandType) {
        return this.executors.get(commandType);
    }

}
