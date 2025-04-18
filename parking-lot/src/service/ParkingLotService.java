package service;

import exceptions.InvalidSlotNumberException;
import exceptions.NoFreeSlotAvailableException;
import exceptions.ParkingLotAlreadyExistException;
import exceptions.SlotNotOccupiedException;
import models.Car;
import models.ParkingLot;
import models.Slot;
import service.strategy.NaturalOrderingParkingStrategy;
import service.strategy.ParkingStrategy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public ParkingLotService() {
        this.parkingStrategy = new NaturalOrderingParkingStrategy();
    };

    public void createParkingLot(Integer capacity) throws ParkingLotAlreadyExistException {

        if (parkingLot != null) {
           throw new ParkingLotAlreadyExistException();
        }
        parkingLot = new ParkingLot();
        for (int i = 1; i <= capacity; i++) {
            parkingLot.setSlot(i, new Slot(i));
        }
        parkingStrategy.createParkingLot(capacity);
    }

    public List<Slot> getSlotsForColor(String color) {
        List<Slot> slots = this.parkingLot.getSlots();
        return slots.stream().filter(s -> s.getCar() != null && s.getCar().getColor().equals(color)).toList();

    }

    public List<Car> getCarsForColor(String color) {
        List<Slot> slots = this.parkingLot.getSlots();
        return slots.stream().filter(s -> s.getCar() != null && s.getCar().getColor().equals(color)).map(Slot::getCar).toList();

    }

    public Slot getSlotNumber(String regNumber) {
        List<Slot> slots = this.parkingLot.getSlots();
         return slots.stream().filter(s -> s.getCar() != null && s.getCar().getRegistrationNumber().equals(regNumber))
                .findFirst().orElse(null);

    }

    public List<Slot> getSlots() {
        return this.parkingLot.getSlots().stream().filter(s -> s.getCar() != null).collect(Collectors.toList());
    }

    public Car leave(Integer slotNumber) throws SlotNotOccupiedException, InvalidSlotNumberException {
        Slot slot = parkingLot.getSlot(slotNumber);
        if(slot == null) throw new InvalidSlotNumberException();
        if(slot.getCar() == null) throw new SlotNotOccupiedException();
        Car car = slot.getCar();
        slot.setCar(null);
        parkingStrategy.freeSlot(slotNumber);
        return car;
    }

    public Integer park(String regNumber, String color) throws NoFreeSlotAvailableException {
        try {
            Integer slotNumber = parkingStrategy.getFreeSlot();
            Car car = new Car(regNumber, color);
            parkingLot.getSlot(slotNumber).setCar(car);
            parkingStrategy.reserveSlot(slotNumber);
            return slotNumber;
        } catch (NoSuchElementException e) {
            throw new NoFreeSlotAvailableException();
        }
    }
}
