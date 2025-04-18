package mode;

import commands.CommandExecutorFactory;
import exceptions.*;
import models.Command;
import service.OutputPrinter;
import service.ParkingLotService;

import java.io.*;

public class FileMode extends Mode {

    public final String fileName;


    public FileMode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory,
                    String filename) {
        super(outputPrinter, commandExecutorFactory);
        this.fileName = filename;

    }

    @Override
    public void process() throws IOException, InvalidCommandException, NoFreeSlotAvailableException, CommandTypeMismatchException, SlotNotOccupiedException, InvalidSlotNumberException, ParkingLotAlreadyExistException {
        File file = new File(fileName);
        final BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            new OutputPrinter().invalidFile(fileName);
            return;
        }
        String line = br.readLine();
        while(line != null) {
            final Command command = new Command(line);
            processCommand(command);
            line = br.readLine();
        }

    }
}
