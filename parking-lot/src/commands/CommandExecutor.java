package commands;

import exceptions.*;
import models.Command;

public interface CommandExecutor {
    public void execute(Command cmd) throws ParkingLotAlreadyExistException, NoFreeSlotAvailableException, InvalidCommandException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException;
}
