package service;

import exceptions.NoFreeSlotAvailableException;
import models.Slot;

import java.util.List;

public class OutputPrinter {

    public void invalidFile(String fileName) {
        printWithNewLine("Invalid File: " + fileName);
    }

    public void welcome() {
        printWithNewLine("Welcome");
    }

    public void invalidRegistrationNumber(String regNumber) {
        printWithNewLine("No Slot found for Registration Number: " + regNumber);
    }

    public void invalidSlotNumber(Integer slotNumber) {
        printWithNewLine("No Slot found for Slot Number: " + slotNumber);
    }

    public void noFreeSlotAvailable() {
        printWithNewLine("No Free Slot Available");
    }

    public void printParkingLotStatus(List<Slot> slots) {
        printWithNewLine("Slot No.    Reg No.      Color");
        slots.forEach(slot -> {printWithNewLine(slot.getSlotNumber() + " " + slot.getCar().getRegistrationNumber() + " " + slot.getCar().getColor());});
    }

    public void slotForRegistration(String regNumber, Integer slotNumber) {
        printWithNewLine("Slot For Registration Number: " + regNumber + " is: " + slotNumber);
    }

    public void slotNotOccupied(Integer slotNumber) {
        printWithNewLine("Slot Not Occupied: " + slotNumber);
    }

    public void exit() {
        printWithNewLine("exiting...");
    }

    public void printSlotsNumber(List<Integer> slotNumbers) {
        printWithNewLine("Number of Slots: " + slotNumbers.size());
    }

    public void carParked(String regNumber, String color, Integer slotNumber) throws NoFreeSlotAvailableException {
        printWithNewLine("Car Parked: " + regNumber + ", " + color + ", " + slotNumber);
    }

    public void leave(String regNumber, String color, Integer slotNumber) {
        printWithNewLine("Car with Reg Number " + regNumber + ", and color " + color + ", leaving slot: " + slotNumber);
    }

    public void parkingLotCreated() {
        printWithNewLine("Parking Lot Created...");
    }

    public void parkingLotAlreadyCreated() {
        printWithNewLine("Parking Lot Already Created...");
    }

    public void printWithNewLine(String msg) {
        System.out.println(msg);
    }
}
